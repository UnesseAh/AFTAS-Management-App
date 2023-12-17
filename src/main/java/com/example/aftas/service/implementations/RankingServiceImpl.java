package com.example.aftas.service.implementations;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;
import com.example.aftas.entities.embeddable.RankId;
import com.example.aftas.repository.RankingRepository;

import com.example.aftas.service.interfaces.CompetitionService;
import com.example.aftas.service.interfaces.MemberService;
import com.example.aftas.service.interfaces.RankingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
    public void changeRankingScore(Competition competition, Member member, Integer fishScore) {
        Ranking ranking = rankingRepository.findRankingByCompetitionAndMember(competition,member);
        ranking.setScore(ranking.getScore() + fishScore);
        rankingRepository.save(ranking);
    }

    @Override
    public List<Ranking> getSortedRankingsByCompetition(Competition competition) {
        return rankingRepository.findRankingsByCompetitionOrderByScoreDesc(competition);
    }

    @Override
    public Ranking registerMember(Ranking ranking) {

        Member member = memberService.getMemberById(ranking.getMember().getNumber());
        Competition competition = competitionService.findByCode(ranking.getCompetition().getCode()).get();

        checkIfMemberAlreadyEnrolledInACompetition(member, competition);

        checkCompetitionDateIsNotOver(competition);

        String generatedNumber = member.getFirstName().substring(0,2) + "-" +
                member.getLastName().substring(0,2) + "-" +
                UUID.randomUUID().toString().replace("-","").substring(0,4);

        Ranking saveRanking = Ranking.builder()
                .id(
                        RankId.builder()
                                .competitionCode(competition.getCode())
                                .memberNumber(member.getNumber()).build()
                )
                .competition(competition)
                .member(member)
                .score(0)
                .rank(0).build();
        return rankingRepository.save(saveRanking);
    }

    @Override
    public void checkIfMemberAlreadyEnrolledInACompetition(Member member, Competition competition) {
        Optional<Ranking> ranking = Optional.ofNullable(rankingRepository.findRankingByCompetitionAndMember(competition, member));
        if (ranking.isPresent()){
            throw new IllegalArgumentException("This member is already registered in this competition");
        }
    }

    @Override
    public void checkCompetitionDateIsNotOver(Competition competition) {
        LocalDateTime dateTimeOfCompetition = LocalDateTime.of(competition.getDate(),competition.getStartTime());
        if (LocalDateTime.now().isAfter(dateTimeOfCompetition.minus(Period.ofDays(1)))) {
            throw new IllegalArgumentException("You can only register in a competition before 24h of its start time");
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
}
