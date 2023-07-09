package com.huh.BaekJoonSupporter.boundedContext.line;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@PreAuthorize("isAnonymous()")
@Controller
@RequestMapping("/line")
public class LineController {

    @GetMapping("/send")
    public String sendMsg() {
        return "/line/message_form";
    }

}
