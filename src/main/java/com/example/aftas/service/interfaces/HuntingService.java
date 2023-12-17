package com.example.aftas.service.interfaces;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Fish;
import com.example.aftas.entities.Hunting;
import com.example.aftas.entities.Member;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface HuntingService {
    Hunting createHunting(Hunting hunting, Long weight);
    void validateMemberAndCompetitionAndFish(Optional<Competition> foundCompetition, Optional<Member> foundMember, Optional<Fish> foundFish, Hunting hunting, Long weight);
    void validateCompetitionStartTimeAndEndTime(Competition competition);
    void validateFish(Optional<Fish> fish, Hunting hunting, Long weight);
    void checkMemberIsAParticipant(Long number, String code);
}
