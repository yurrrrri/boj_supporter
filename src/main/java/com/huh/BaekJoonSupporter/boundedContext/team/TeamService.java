package com.huh.BaekJoonSupporter.boundedContext.team;

import com.huh.BaekJoonSupporter.boundedContext.line.Line;
import com.huh.BaekJoonSupporter.boundedContext.member.Member;
import com.huh.BaekJoonSupporter.base.customException.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public Team create(Long leaderId, String teamName, Line line, Member leader) {
        Team team = Team.builder()
                .leaderId(leaderId)
                .teamName(teamName)
                .line(line)
                .build();
        addMember(team, leader);
        teamRepository.save(team);
        return team;
    }

    public Team getTeamByName(String teamName) {
        return teamRepository.findByTeamName(teamName)
                .orElseThrow(() -> new DataNotFoundException("Comment not found."));
    }

    public Team getTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Comment not found."));
    }

    public Team modify(Team team, String teamName, Line line) {
        Team modifyTeam = team.toBuilder()
                .teamName(teamName)
                .line(line)
                .build();
        teamRepository.save(modifyTeam);
        return modifyTeam;
    }

    public void delete(Team team) {
        this.teamRepository.delete(team);
    }

    public void addMember(Team team, Member member) {
        team.getMembers().add(member);
        teamRepository.save(team);
    }
}

