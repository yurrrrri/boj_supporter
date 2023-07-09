package com.huh.BaekJoonSupporter.boundedContext.teamrule;

import com.huh.BaekJoonSupporter.boundedContext.team.Team;
import com.huh.BaekJoonSupporter.base.customException.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamRuleService {
    private final TeamRuleRepository teamRuleRepository;

    public TeamRule create(Team team, String target, String difficulty, Long targetNumber) {
        TeamRule teamRule = TeamRule.builder()
                .target(target)
                .difficulty(difficulty)
                .targetNumber(targetNumber)
                .team(team)
                .build();

        teamRuleRepository.save(teamRule);
        return teamRule;
    }

    public TeamRule getTeamRule(Long id) {
        Optional<TeamRule> teamRule = teamRuleRepository.findById(id);
        if (teamRule.isPresent()) {
            return teamRule.get();
        } else {
            throw new DataNotFoundException("조회할 규칙이 없습니다.");
        }
    }

    public void modify(TeamRule teamRule, String changeTarget, String changeDifficulty, Long changeTargetNumber) {
        TeamRule teamRule1 = teamRule.toBuilder()
                .target(changeTarget)
                .difficulty(changeDifficulty)
                .targetNumber(changeTargetNumber)
                .build();
        teamRuleRepository.save(teamRule1);
    }

    public void delete(TeamRule teamRule) {
        teamRuleRepository.delete(teamRule);
    }

}
