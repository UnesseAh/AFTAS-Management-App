package com.example.aftas;

import com.example.aftas.entities.*;
import com.example.aftas.handler.exception.ValidationException;
import com.example.aftas.repository.HuntingRepository;
import com.example.aftas.service.implementations.HuntingServiceImpl;
import com.example.aftas.service.interfaces.CompetitionService;
import com.example.aftas.service.interfaces.FishService;
import com.example.aftas.service.interfaces.MemberService;
import com.example.aftas.service.interfaces.RankingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class HuntingServiceTests {

    private HuntingServiceImpl huntingService;

    @Mock
    private HuntingRepository huntingRepository;

    @Mock
    private CompetitionService competitionService;

    @Mock
    private MemberService memberService;

    @Mock
    private FishService fishService;

    @Mock
    private RankingService rankingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        huntingService = new HuntingServiceImpl(huntingRepository, competitionService, memberService, fishService, rankingService);
    }

    @Test
    public void testCreateHuntingWithNullCompetition() {
        Hunting hunting = Hunting.builder()
                .member(Member.builder().number(13L).build())
                .fish(Fish.builder().name("samaka").build())
                .build();

        Mockito.when(huntingRepository.save(hunting)).thenReturn(hunting);

        assertThrows(NullPointerException.class, () -> huntingService.createHunting(hunting, 10L),
                "Competition must not be null");
    }

    @Test
    public void testCreateHuntingWithNonExistentMember() {
        Hunting hunting = Hunting.builder()
                .competition(Competition.builder().code("casa-2023-12-22").build())
                .member(Member.builder().number(13L).build())
                .fish(Fish.builder().name("samaka").build())
                .build();

        Mockito.when(huntingRepository.save(hunting)).thenReturn(hunting);
        Mockito.when(competitionService.findByCode("casa-2023-12-22")).thenReturn(Optional.of(new Competition()));
        Mockito.when(memberService.getMemberByNumber(13L)).thenReturn(Optional.empty());

        assertThrows(ValidationException.class, () -> huntingService.createHunting(hunting, 10L),
                "Member must exist");
    }

    @Test
    public void testCreateHuntingWithNonExistentFish() {
        Hunting hunting = Hunting.builder()
                .competition(Competition.builder().code("casa-2023-12-22").build())
                .member(Member.builder().number(13L).build())
                .fish(Fish.builder().name("samaka").build())
                .build();

        Mockito.when(huntingRepository.save(hunting)).thenReturn(hunting);
        Mockito.when(competitionService.findByCode("casa-2023-12-22")).thenReturn(Optional.of(new Competition()));
        Mockito.when(memberService.getMemberByNumber(13L)).thenReturn(Optional.of(new Member()));
        Mockito.when(fishService.getFishByName("samaka")).thenReturn(Optional.empty());

        assertThrows(ValidationException.class, () -> huntingService.createHunting(hunting, 10L),
                "Fish must exist");
    }

}
