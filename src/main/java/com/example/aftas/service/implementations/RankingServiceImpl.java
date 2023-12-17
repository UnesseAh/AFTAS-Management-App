package com.example.aftas.service.implementations;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;
import com.example.aftas.entities.embeddable.RankId;
import com.example.aftas.handler.exception.ValidationException;
import com.example.aftas.repository.RankingRepository;

import com.example.aftas.service.interfaces.CompetitionService;
import com.example.aftas.service.interfaces.MemberService;
import com.example.aftas.service.interfaces.RankingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class RankingServiceImpl implements RankingService {

    private final RankingRepository rankingRepository;
    private final MemberService memberService;
    private final CompetitionService competitionService;

    public RankingServiceImpl(RankingRepository rankingRepository, MemberService memberService, CompetitionService competitionService) {
        this.rankingRepository = rankingRepository;
        this.memberService = memberService;
        this.competitionService = competitionService;
    }

    @Override
    public Ranking registerMember(Ranking ranking) {
        Optional<Member> foundMember = memberService.getMemberById(ranking.getMember().getNumber());
        Optional<Competition> foundCompetition = competitionService.findByCode(ranking.getCompetition().getCode());

        checkMemberAndCompetitionExist(foundMember, foundCompetition, ranking);

        Member member = foundMember.get(); Competition competition = foundCompetition.get();

        checkIfMemberAlreadyEnrolledInACompetition(member, competition);

        checkCompetitionDateIsNotOver(competition);

        Ranking saveRanking = Ranking.builder()
                .id(
                        RankId.builder()
                        .competitionCode(competition.getCode())
                        .memberNumber(member.getNumber())
                        .build()
                )
                .competition(competition)
                .member(member)
                .score(0)
                .rank(0)
                .build();

        return rankingRepository.save(saveRanking);
    }

    @Override
    public void checkIfMemberAlreadyEnrolledInACompetition(Member member, Competition competition) {
        Optional<Ranking> ranking = rankingRepository.findRankingByCompetitionAndMember(competition.getCode(), member.getNumber());
        if (ranking.isPresent()){
            throw new IllegalArgumentException("This member is already registered in this competition");
        }
    }

    @Override
    public void checkMemberAndCompetitionExist(Optional<Member> foundMember, Optional<Competition> foundCompetition, Ranking ranking) {
        List<String> errors = new ArrayList<>();

        if(foundMember.isEmpty()){
            errors.add("Member with number {" + ranking.getMember().getNumber() + "} doesn't exist");
        }
        if (foundCompetition.isEmpty()){
            errors.add("Competition with code {" + ranking.getCompetition().getCode() + "} doesn't exist");
        }

        if(!errors.isEmpty()){
            throw new ValidationException(errors);
        }
    }

    @Override
    public void checkCompetitionDateIsNotOver(Competition competition) {
        LocalDateTime dateTimeOfCompetition = LocalDateTime.of(competition.getDate(),competition.getStartTime());
        if (LocalDateTime.now().isAfter(dateTimeOfCompetition.minus(Period.ofDays(1)))) {
            throw new IllegalArgumentException("You can register in a competition only before 24h of its starting date");
        }
    }

    @Override
    public List<Ranking> generateCompetitionRanks(String competitionCode) {
        Optional<Competition> competition = competitionService.findByCode(competitionCode);
        List<Ranking> sortedRankingsByCompetition = getSortedRankingsByCompetition(competition.get());

        AtomicInteger count = new AtomicInteger(0);
        sortedRankingsByCompetition.forEach(ranking -> ranking.setRank(count.incrementAndGet()));

        return rankingRepository.saveAll(sortedRankingsByCompetition);
    }

    @Override
    public List<Ranking> showCompetitionPodium(String competitionCode) {
        return generateCompetitionRanks(competitionCode).stream().limit(3).toList();
    }

    @Override
    public void changeRankingScore(Competition competition, Member member, Integer fishScore) {
        Optional<Ranking> ranking = rankingRepository.findRankingByCompetitionAndMember(competition.getCode(),member.getNumber());
        ranking.get().setScore(ranking.get().getScore() + fishScore);
        rankingRepository.save(ranking.get());
    }

    @Override
    public List<Ranking> getSortedRankingsByCompetition(Competition competition) {
        return rankingRepository.findRankingsByCompetitionOrderByScoreDesc(competition);
    }


}
