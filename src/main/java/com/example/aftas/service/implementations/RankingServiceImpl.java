package com.example.aftas.service.implementations;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;
import com.example.aftas.handler.exception.ResourceNotFoundException;
import com.example.aftas.repository.RankingRepository;
import com.example.aftas.service.interfaces.CompetitionService;
import com.example.aftas.service.interfaces.MemberService;
import com.example.aftas.service.interfaces.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService {

    private final RankingRepository rankingRepository;
    private final MemberService memberService;
    private final CompetitionService competitionService;


    @Override
    public Ranking registerMemberInACompetition(Long memberId, String competitionCode) {

        Member member = memberService.getMemberById(memberId);
        Competition competition = competitionService.findCompetitionByCode(competitionCode);

        checkIfMemberAlreadyEnrolledInACompetition(member, competition);

        checkCompetitionDateIsNotOver(competition);

        Ranking ranking = new Ranking().builder().member(member).competition(competition).score(0).rank(0).build();

        return rankingRepository.save(ranking);
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


}
