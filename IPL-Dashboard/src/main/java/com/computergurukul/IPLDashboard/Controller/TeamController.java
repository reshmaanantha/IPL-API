package com.computergurukul.IPLDashboard.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.computergurukul.IPLDashboard.Repository.MatchRepository;
import com.computergurukul.IPLDashboard.Repository.TeamRepository;
import com.computergurukul.IPLDashboard.model.Match;
import com.computergurukul.IPLDashboard.model.Team;

@RestController
public class TeamController {

	private TeamRepository teamRepository;
	private MatchRepository matchRepository;
	final int COUNT=4;
	public TeamController(TeamRepository teamRepository,MatchRepository matchRepository) {
		
		this.teamRepository = teamRepository;
		this.matchRepository=matchRepository;
	}
	@GetMapping("/team")
	public Iterable<Team> getAllTeam(){
	return this.teamRepository.findAll();
		
	}

	@GetMapping("/team/{teamName}")
	public Team getTeam(@PathVariable("teamName") String teamName) {
		Team team=this.teamRepository.findByTeamName(teamName);
		team.setMatches(matchRepository.findLatestMatchesByTeam(teamName,COUNT));
		return team;
		
	}
	
	@GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);
        return this.matchRepository.getMatchesByTeamBetweenDates(
            teamName,
            startDate,
            endDate
            );
    }
}
