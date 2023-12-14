package com.example.aftas.service.interfaces;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompetitionService {
    Competition createCompetition(Competition competition);
    String generateCompetitionCode(Competition competition);
    Competition findCompetitionByCode(String code);
    List<Competition> getAllCompetitions();
    List<Ranking> generateCompetitionRanks(String competitionCode);
    List<Ranking> showCompetitionPodium(String competitionCode);
    Competition updateCompetition(Long id, Competition competition);
    void deleteCompetition(Long id);
    Ranking registerMemberInACompetition(Long memberId, String competitionId);
    void checkIfMemberAlreadyEnrolledInACompetition(Member member, Competition competition);
    void checkCompetitionDateIsNotOver(Competition competition);
}
