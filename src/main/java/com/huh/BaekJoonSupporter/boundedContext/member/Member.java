package com.huh.BaekJoonSupporter.boundedContext.member;

import com.huh.BaekJoonSupporter.boundedContext.base.BaseEntity;
import com.huh.BaekJoonSupporter.boundedContext.board.Board;
import com.huh.BaekJoonSupporter.boundedContext.comment.Comment;
import com.huh.BaekJoonSupporter.boundedContext.team.Team;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String token;

    @OneToMany(mappedBy = "member", cascade = ALL)
    @Builder.Default
    private List<Board> boards = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    @OneToMany(mappedBy = "member", cascade = ALL)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

}