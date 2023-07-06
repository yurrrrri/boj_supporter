package com.huh.BaekJoonSupporter.boudedContext.teamrule;

import com.huh.BaekJoonSupporter.boundedContext.line.Line;
import com.huh.BaekJoonSupporter.boundedContext.member.Member;
import com.huh.BaekJoonSupporter.boundedContext.member.MemberService;
import com.huh.BaekJoonSupporter.boundedContext.team.Team;
import com.huh.BaekJoonSupporter.boundedContext.team.TeamService;
import com.huh.BaekJoonSupporter.boundedContext.teamrule.TeamRule;
import com.huh.BaekJoonSupporter.boundedContext.teamrule.TeamRuleRepository;
import com.huh.BaekJoonSupporter.boundedContext.teamrule.TeamRuleService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@TestPropertySource(properties = {"spring.config.location=classpath:application.yml"})
public class TeamRuleServiceTest {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamRuleService teamRuleService;

    @Autowired
    private TeamRuleRepository teamRuleRepository;

    @Autowired
    private MemberService memberService;

    private final String NAME1 = "user1";
    private final String NAME2 = "user2";
    private final String PASSWORD = "1234";
    private final String TOKEN1 = "ASDF";
    private final String TOKEN2 = "QWER";
    private final String TEAM_NAME = "teamName";

    @Test
    @DisplayName("TeamRule 테스트")
    void test001() {
        memberService.create(NAME1, PASSWORD, TOKEN1);
        memberService.create(NAME2, PASSWORD, TOKEN2);
        Member leader = memberService.getMember(NAME1);
        Line line = null;
        Team team = teamService.create(leader.getId(), TEAM_NAME, line, leader);

        // Create
        TeamRule teamRule = teamRuleService.create(team, "aaa", "mnmm", 5L);
        TeamRule temp = teamRuleService.getTeamRule(1L);

        assertThat(teamRule).isEqualTo(temp);

        // Update
        String changeTarget = "bbb";
        String changeDifficulty = "ccc";
        Long changeTargetNumber = 3L;
        teamRuleService.modify(teamRule, changeTarget, changeDifficulty, changeTargetNumber);
        assertThat(teamRule.getTargetNumber()).isEqualTo(3L);

        // Delete
        teamRuleService.delete(teamRule);
        Optional<TeamRule> ot = teamRuleRepository.findById(1L);
        assertThat(ot.isEmpty()).isEqualTo(true);
    }
}

