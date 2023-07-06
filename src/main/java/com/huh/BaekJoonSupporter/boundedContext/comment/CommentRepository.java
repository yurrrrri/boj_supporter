package com.huh.BaekJoonSupporter.boundedContext.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByContent(String content);

    Optional<Comment> findByBoard(Comment comment);
}
