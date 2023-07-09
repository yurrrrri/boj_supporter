package com.huh.BaekJoonSupporter.boundedContext.line;

import com.huh.BaekJoonSupporter.boundedContext.line.form.LineMessageForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@PreAuthorize("isAnonymous()")
@RestController
@RequestMapping("/line")
public class LineApiManager {

    @Value("${custom.line.token}")
    private String my_token;
    private static final String api_url = "https://notify-api.line.me/api/notify?message=";
    private static final String HEADER_AUTH = "Authorization";

    @PostMapping("/callTest1")
    public String callAPI(@Valid LineMessageForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/member/login";
        }

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HEADER_AUTH, my_token);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        String apiContext = api_url + form.getMessage();

        ResponseEntity<String> jsonObject = restTemplate.exchange(apiContext, HttpMethod.POST, httpEntity, String.class);

        String response = jsonObject.toString();

        return "redirect:/line/message_form";
    }

}
