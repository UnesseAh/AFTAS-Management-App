package com.example.aftas.repository;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;
import com.example.aftas.entities.embeddable.RankId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, RankId> {
    @Query("SELECT r FROM Ranking r WHERE r.member.number = ?1 AND r.competition.code = ?2")
    Optional<Ranking> findRankingByCompetitionAndMember(Long member, String competition);
    List<Ranking> findRankingsByCompetitionOrderByScoreDesc(Competition competition);
    @Query("SELECT COUNT(*) FROM Ranking r WHERE r.competition.code = ?1")
    Integer getNumberOfMembers(String code);
}
