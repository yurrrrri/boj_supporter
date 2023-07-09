package com.huh.BaekJoonSupporter.boundedContext.comment;

import com.huh.BaekJoonSupporter.boundedContext.board.Board;
import com.huh.BaekJoonSupporter.boundedContext.member.Member;
import com.huh.BaekJoonSupporter.customException.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment create(String content, Board board, Member member) {
        Comment comment = Comment.builder()
                .content(content)
                .board(board)
                .member(member)
                .build();

        board.getComments().add(comment);
        member.getComments().add(comment);
        commentRepository.save(comment);
        return comment;
    }

    public Comment getComment(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        return comment.orElseThrow(() -> new DataNotFoundException("comment not found"));
    }

    public void modify(Comment comment, String contents) {
        Comment modifyComment = comment.toBuilder()
                .content(contents)
                .build();
        commentRepository.save(modifyComment);
    }

    public void delete(Comment comment) {
        Board board = comment.getBoard();
        board.getComments().remove(comment);
        commentRepository.delete(comment);
    }

}
