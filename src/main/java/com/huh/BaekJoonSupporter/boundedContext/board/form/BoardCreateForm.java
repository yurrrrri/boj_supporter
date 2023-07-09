package com.huh.BaekJoonSupporter.boundedContext.board.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardCreateForm {

    @Size(min = 1, max = 150, message = "제목은 1자 이상 150자 이하여야 합니다.")
    private String title;

    @NotEmpty(message = "내용을 입력해주세요.")
    private String post;

    private String category;

}
