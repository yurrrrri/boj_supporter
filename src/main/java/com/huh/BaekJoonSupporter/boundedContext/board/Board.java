package com.huh.BaekJoonSupporter.boundedContext.board;

import com.huh.BaekJoonSupporter.boundedContext.base.BaseEntity;
import com.huh.BaekJoonSupporter.boundedContext.category.Category;
import com.huh.BaekJoonSupporter.boundedContext.comment.Comment;
import com.huh.BaekJoonSupporter.boundedContext.member.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Board extends BaseEntity {

    private String title;

    private String post;

    private Integer view;

    private int recommend;

    @ManyToOne(fetch = LAZY)
    private Member member;

    @ManyToOne(fetch = LAZY)
    public Category category;

    @OneToMany(mappedBy = "board")
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    protected static Board create(String title, String post, Member member) {
        Board board = Board.builder()
                .title(title)
                .post(post)
                .member(member)
                .view(0)
                .recommend(0)
                .build();
        member.getBoards().add(board);
        return board;
    }

    protected static Board create(String title, String post, Member member, Category category) {
        Board board = Board.builder()
                .title(title)
                .post(post)
                .member(member)
                .category(category)
                .view(0)
                .recommend(0)
                .build();
        member.getBoards().add(board);
        category.getBoards().add(board);
        return board;
    }

    protected Board modify(String title, String post) {
        return this.toBuilder()
                .title(title)
                .post(post)
                .build();
    }

    protected void addView() {
        this.view++;
    }

    protected void plusRecommend() {
        this.recommend++;
    }

    protected void minusRecommend() {
        this.recommend--;
    }
}