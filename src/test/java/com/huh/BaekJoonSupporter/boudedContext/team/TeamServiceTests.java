package com.huh.BaekJoonSupporter.boudedContext.team;

import com.huh.BaekJoonSupporter.boundedContext.line.Line;
import com.huh.BaekJoonSupporter.boundedContext.member.Member;
import com.huh.BaekJoonSupporter.boundedContext.member.MemberService;
import com.huh.BaekJoonSupporter.boundedContext.team.Team;
import com.huh.BaekJoonSupporter.boundedContext.team.TeamRepository;
import com.huh.BaekJoonSupporter.boundedContext.team.TeamService;
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
public class TeamServiceTests {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MemberService memberService;

    private final String NAME1 = "user1";
    private final String NAME2 = "user2";
    private final String PASSWORD = "1234";
    private final String TOKEN1 = "ASDF";
    private final String TOKEN2 = "QWER";
    private final String TEAM_NAME = "teamName";

    @Test
    @DisplayName("TeamService CRUD Test")
    void test001() {
        this.memberService.create(NAME1, PASSWORD, TOKEN1);
        this.memberService.create(NAME2, PASSWORD, TOKEN2);

        ////////////////////////
        // Create 테스트
        ////////////////////////
        Member leader = this.memberService.getMember(NAME1);
        Line line = null;
        Team team = this.teamService.create(leader.getId(), TEAM_NAME, line, leader);
        Team temp = this.teamService.getTeamByName(TEAM_NAME);

        assertThat(team).isEqualTo(temp);

        ////////////////////////
        // Update 테스트
        ////////////////////////
        String modifyName = "nameTeam";
        Member member = this.memberService.getMember(NAME2);
        teamService.modify(team, modifyName, null);
        temp = this.teamService.getTeamByName(modifyName);

        assertThat(temp.getTeamName()).isEqualTo(modifyName);

        ////////////////////////
        // Delete 테스트
        ////////////////////////
        String teamName = temp.getTeamName();
        this.teamService.delete(temp);
        Optional<Team> ot = this.teamRepository.findByTeamName(teamName);
        assertThat(ot.isEmpty()).isEqualTo(true);
    }

}
