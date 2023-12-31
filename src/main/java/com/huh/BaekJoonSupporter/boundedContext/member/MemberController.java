package com.huh.BaekJoonSupporter.boundedContext.member;

import com.huh.BaekJoonSupporter.boundedContext.member.form.MemberCreateForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/signup")
    public String signup() {
        return "/member/signup_form";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/signup")
    public String signup(@Valid MemberCreateForm form) {
        memberService.create(form);
        return "redirect:/";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String login() {
        return "/member/login_form";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public String showMe() {
        return "/member/me";
    }
}
