package com.huh.BaekJoonSupporter.boundedContext.team;

import com.huh.BaekJoonSupporter.boundedContext.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;
    private final MemberService memberService;

    @GetMapping("/info/{id}")
    public String showInfo(@PathVariable Long id, Model model) {
        Team team = teamService.getTeamById(id);
        model.addAttribute("team", team);
        return "/team/team_info";
    }

    @GetMapping("/create")
    public String create() {
        return "/team/create";
    }

    @GetMapping("/add")
    public String add() {
        return "/team/add";
    }

    @GetMapping("/modify")
    public String modify() {
        return "/team/modify";
    }
}
