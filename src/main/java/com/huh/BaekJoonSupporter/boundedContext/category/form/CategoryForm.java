package com.huh.BaekJoonSupporter.boundedContext.category.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryForm {

    @NotEmpty(message = "제목을 입력해주세요.")
    @Size(min = 1, max = 50)
    private String name;

    @NotEmpty(message = "카테고리 소개를 입력해주세요.")
    @Max(100)
    private String about;
}
