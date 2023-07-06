package com.huh.BaekJoonSupporter.solvedApi.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public class UserDto {
    private Integer solvedCount;
}
