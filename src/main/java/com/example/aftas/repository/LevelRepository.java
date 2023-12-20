package com.example.aftas.repository;

import com.example.aftas.entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
    Optional<Level> getLevelByCode(Integer code);

    @Query("SELECT l.points from Level l where l.code < ?1 order by l.code desc limit 1")
    Integer getPointsOfSmallestLevel(Integer levelCode);
    @Query("SELECT l.points from Level l where l.code > ?1 order by l.code asc limit 1")
    Integer getPointsOfLargestLevel(Integer levelCode);
}
