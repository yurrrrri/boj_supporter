package com.huh.BaekJoonSupporter.boundedContext.line.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LineMessageForm {

    @NotEmpty(message = "전송할 메세지를 입력해주세요.")
    private String message;

}
