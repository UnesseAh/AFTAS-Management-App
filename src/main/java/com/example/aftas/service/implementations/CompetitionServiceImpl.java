package com.example.aftas.service.implementations;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;
import com.example.aftas.entities.embeddable.RankId;
import com.example.aftas.handler.exception.ResourceNotFoundException;
import com.example.aftas.handler.exception.ValidationException;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.repository.RankingRepository;
import com.example.aftas.service.interfaces.CompetitionService;
import com.example.aftas.service.interfaces.MemberService;
import com.example.aftas.service.interfaces.RankingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final RankingRepository rankingRepository;
    private final RankingService rankingService;
    private final MemberService memberService;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository, RankingRepository rankingRepository, RankingService rankingService, MemberService memberService) {
        this.competitionRepository = competitionRepository;
        this.rankingRepository = rankingRepository;
        this.rankingService = rankingService;
        this.memberService = memberService;
    }

    @Override
    public Competition createCompetition(Competition competition) {
        List<String> errors = new ArrayList<>();
        if(competitionRepository.findCompetitionByDate(competition.getDate()).isPresent()){
            errors.add("There is already a competition in the date (" + competition.getDate() + ")");
        }
        if(competition.getEndTime().isBefore(competition.getStartTime())){
            errors.add("the competition's end time must come after the start time");
        }
        if ( Duration.between(competition.getStartTime(), competition.getEndTime()).toHours() < 1 ){
            errors.add("The competition must be at least 1 hour long");
        }
        if(competition.getDate().minus(Period.ofDays(2)).isBefore(LocalDate.now())){
            errors.add("The competition must at least be in 2 days from now");
        }
        if(!errors.isEmpty()){
            throw new ValidationException(errors);
        }

        String generatedCode = MessageFormat.format(
                "{0}-{1}",
                competition.getLocation().substring(0,4).toLowerCase(),
                competition.getDate());

        competition.setCode(generatedCode);
        competition.setNumberOfParticipants(competition.getNumberOfParticipants());
        return competitionRepository.save(competition);
    }

    @Override
    public Competition findCompetitionByCode(String code) {
        Optional<Competition> competition = competitionRepository.findCompetitionByCode(code);
        if (competition.isEmpty()){
            throw new ResourceNotFoundException("Competition with the code (" + code + ") doesn't exist");
        }
        return competition.get();
    }

    @Override
    public Ranking registerMemberInACompetition(Long memberId, String competitionCode) {

        Member member = memberService.getMemberById(memberId);
        Competition competition = findCompetitionByCode(competitionCode);

        checkIfMemberAlreadyEnrolledInACompetition(member, competition);

        checkCompetitionDateIsNotOver(competition);

        String generatedNumber = member.getFirstName().substring(0,2) + "-" +
                member.getLastName().substring(0,2) + "-" +
                UUID.randomUUID().toString().replace("-","").substring(0,4);

        Ranking ranking = Ranking.builder()
                        .id(
                                RankId.builder()
                                        .competitionCode(competition.getCode())
                                        .memberNumber(member.getNumber()).build()
                        )
                        .competition(competition)
                        .member(member)
                        .score(0)
                        .rank(0).build();
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

    @Override
    public List<Ranking> generateCompetitionRanks(String competitionCode) {
        Optional<Competition> competition = competitionRepository.findCompetitionByCode(competitionCode);
        List<Ranking> sortedRankingsByCompetition = rankingService.getSortedRankingsByCompetition(competition.get());

        AtomicInteger count = new AtomicInteger(0);
        sortedRankingsByCompetition.forEach(ranking -> ranking.setRank(count.incrementAndGet()));

        return rankingRepository.saveAll(sortedRankingsByCompetition);
    }

    @Override
    public List<Ranking> showCompetitionPodium(String competitionCode) {
        return generateCompetitionRanks(competitionCode).stream().limit(3).toList();
    }

    @Override
    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    @Override
    public Competition updateCompetition(Long id, Competition competition) {
        return null;
    }

    @Override
    public void deleteCompetition(Long id) {

    }
}
