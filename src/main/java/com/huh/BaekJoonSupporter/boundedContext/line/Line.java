package com.huh.BaekJoonSupporter.boundedContext.line;

import com.huh.BaekJoonSupporter.base.entity.BaseEntity;
import com.huh.BaekJoonSupporter.boundedContext.team.Team;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Line extends BaseEntity {

    @Column(unique = true)
    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    private Team team;

}