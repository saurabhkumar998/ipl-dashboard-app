package com.saurabh.ipldashboard.controller;

import com.saurabh.ipldashboard.model.Team;
import com.saurabh.ipldashboard.repository.MatchRepository;
import com.saurabh.ipldashboard.repository.TeamRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TeamController {
    
    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository){
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName){
        Team team = this.teamRepository.findByTeamName(teamName);
        team.setMatches(matchRepository.findLatestMatchesbyTeam(teamName, 4));

        return team;
    }
}
