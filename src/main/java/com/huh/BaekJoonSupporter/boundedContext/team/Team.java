package com.huh.BaekJoonSupporter.boundedContext.team;

import com.huh.BaekJoonSupporter.base.entity.BaseEntity;
import com.huh.BaekJoonSupporter.boundedContext.line.Line;
import com.huh.BaekJoonSupporter.boundedContext.member.Member;
import com.huh.BaekJoonSupporter.boundedContext.teamrule.TeamRule;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Team extends BaseEntity {

    private Long leaderId;

    @Column(unique = true)
    private String teamName;

    private String details;

    @OneToOne
    private Line line;

    @OneToMany(mappedBy = "team")
    @Builder.Default
    private List<TeamRule> teamRules = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    @Builder.Default
    private List<Member> members = new ArrayList<>();

}