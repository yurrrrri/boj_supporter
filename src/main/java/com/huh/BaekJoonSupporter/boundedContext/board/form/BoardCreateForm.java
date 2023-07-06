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

    @NotEmpty(message = "제목을 입력해주세요.")
    @Size(min = 1, max = 150)
    private String title;

    @NotEmpty(message = "내용을 입력해주세요.")
    private String post;

    private String category;

}
