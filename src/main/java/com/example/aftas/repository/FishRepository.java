package com.example.aftas.repository;

import com.example.aftas.entities.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FishRepository extends JpaRepository<Fish, Long> {
    Optional<Fish> findFishByName(String name);
}
