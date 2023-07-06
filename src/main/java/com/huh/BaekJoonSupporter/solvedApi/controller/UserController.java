package com.huh.BaekJoonSupporter.solvedApi.controller;

import com.huh.BaekJoonSupporter.boundedContext.teamrule.TeamRuleService;
import com.huh.BaekJoonSupporter.solvedApi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    private TeamRuleService teamRuleService;

    @GetMapping("/test")
    public void abc() throws IOException, ParseException {
        Long temp = userService.getTier("Gold");
        System.out.println(temp);
    }

    @GetMapping("/test2")
    public void abc1() throws IOException, ParseException {
        Long temp = userService.getSolvedCount();
        System.out.println(temp);
    }
}
