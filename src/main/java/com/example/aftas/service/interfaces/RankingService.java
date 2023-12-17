package com.example.aftas.service.interfaces;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RankingService {

    void changeRankingScore(Competition competition, Member member, Integer fishScore);
    List<Ranking> getSortedRankingsByCompetition(Competition competition);
    Ranking registerMember(Ranking ranking);
    List<Ranking> generateCompetitionRanks(String competitionCode);
    List<Ranking> showCompetitionPodium(String competitionCode);
    void checkCompetitionDateIsNotOver(Competition competition);
    void checkIfMemberAlreadyEnrolledInACompetition(Member member, Competition competition);
    void checkMemberAndCompetitionExist(Optional<Member> member, Optional<Competition> competition, Ranking ranking);


}
