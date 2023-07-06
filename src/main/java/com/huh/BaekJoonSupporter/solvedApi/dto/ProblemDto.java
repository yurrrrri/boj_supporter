package com.huh.BaekJoonSupporter.solvedApi.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public class ProblemDto {
    private String level;
    private Integer solved;
}
