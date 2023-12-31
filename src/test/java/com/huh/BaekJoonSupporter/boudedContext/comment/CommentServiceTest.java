package com.huh.BaekJoonSupporter.boudedContext.comment;

import com.huh.BaekJoonSupporter.boundedContext.board.Board;
import com.huh.BaekJoonSupporter.boundedContext.board.BoardRepository;
import com.huh.BaekJoonSupporter.boundedContext.board.BoardService;
import com.huh.BaekJoonSupporter.boundedContext.comment.Comment;
import com.huh.BaekJoonSupporter.boundedContext.comment.CommentRepository;
import com.huh.BaekJoonSupporter.boundedContext.comment.CommentService;
import com.huh.BaekJoonSupporter.boundedContext.member.Member;
import com.huh.BaekJoonSupporter.boundedContext.member.MemberRepository;
import com.huh.BaekJoonSupporter.boundedContext.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@TestPropertySource(properties = {"spring.config.location=classpath:application.yml"})
class CommentServiceTest {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    private Long createBoard(String title, String post, Member member) {
        return boardService.create(title, post, member);
    }

    @Test
    void CommentTests() {
        // 게시글 작성 //
        Member member = this.memberService.create("name", "", "");
        Long boId = createBoard("제목", "내용", member);
        Board board = this.boardService.getBoard(boId);

        // 댓글 생성 //
        Comment comment = this.commentService.create("테스트 댓글", boardService.getBoard(boId), memberService.getMember(member.getUsername()));
        assertThat(comment.getContent()).isEqualTo("테스트 댓글");

        // 댓글 수정 //
        Optional<Comment> checkco = this.commentRepository.findByContent("테스트 댓글");
        if (!checkco.isPresent()) ;
        Comment comment1 = checkco.get();
        Comment comment2 = checkco.get();
        commentService.modify(comment1, "수정");
        assertThat(comment1.getContent()).isEqualTo("수정");
        assertThat(comment1.getBoard().getComments()).isEqualTo(comment2.getBoard().getComments());

        //댓글 삭제 //
        Optional<Comment> checkco1 = this.commentRepository.findByContent("수정");
        if (!checkco1.isPresent()) ;
        Comment comment12 = checkco1.get();
        Comment comment3 = checkco1.get();
        commentService.delete(comment12);
        assertTrue(!comment12.getContent().isEmpty());
        assertThat(comment12.getBoard().getComments()).isEqualTo(comment3.getBoard().getComments());
        assertThat(comment12.getBoard().getComments().size()).isEqualTo(0);

    }
}