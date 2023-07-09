package com.huh.BaekJoonSupporter.boundedContext.teamrule;


import com.huh.BaekJoonSupporter.boundedContext.member.MemberService;
import com.huh.BaekJoonSupporter.boundedContext.team.Team;
import com.huh.BaekJoonSupporter.boundedContext.teamrule.form.TeamRuleCreateForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teamrule")
public class TeamRuleController {

    private final TeamRuleService teamRuleService;

    private final MemberService memberService;

    @GetMapping("/list")
    public String showList() {
        return "layout";
    }

    @GetMapping("/create")
    public String create() {
        return "teamrule/create";
    }

    @PostMapping("/create")
    @ResponseBody
    public String create(@Valid TeamRuleCreateForm createForm, Principal principal) {
        Team team = memberService.getMember(principal.getName()).getTeam();
        TeamRule teamRule = null;

        teamRule = (createForm.getTarget().equals("무관") && !createForm.getDifficulty().equals("default")) ?
                teamRuleService.create(team, createForm.getTarget(), "default", createForm.getTargetNumber()) :
                teamRuleService.create(team, createForm.getTarget(), createForm.getDifficulty(), createForm.getTargetNumber());

        if (teamRule == null) return "redirect:/teamrule/list";

        return "redirect:/teamrule/list";

    }

}
