package com.huh.BaekJoonSupporter.boundedContext.member;

import com.huh.BaekJoonSupporter.boundedContext.board.Board;
import com.huh.BaekJoonSupporter.boundedContext.comment.Comment;
import com.huh.BaekJoonSupporter.boundedContext.team.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String token;

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Board> boards = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

}