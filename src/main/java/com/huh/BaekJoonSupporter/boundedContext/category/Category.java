package com.huh.BaekJoonSupporter.boundedContext.category;

import com.huh.BaekJoonSupporter.boundedContext.base.BaseEntity;
import com.huh.BaekJoonSupporter.boundedContext.board.Board;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Category extends BaseEntity {

    private String name;

    private String about;

    @Builder.Default
    @OneToMany(mappedBy = "category", cascade = ALL)
    private List<Board> boards = new ArrayList<>();

    protected static Category create(String name, String about) {
        return Category.builder()
                .name(name)
                .about(about)
                .build();
    }

    protected Category modify(String name, String about) {
        return this.toBuilder()
                .name(name)
                .about(about)
                .build();
    }
}
