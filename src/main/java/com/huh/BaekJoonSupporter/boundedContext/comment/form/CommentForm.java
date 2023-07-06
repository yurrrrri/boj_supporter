package com.huh.BaekJoonSupporter.boundedContext.comment.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentForm {
    @NotEmpty(message = "댓글을 입력해주세요")
    private String comment;
}
