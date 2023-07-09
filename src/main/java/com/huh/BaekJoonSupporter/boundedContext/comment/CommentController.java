package com.huh.BaekJoonSupporter.boundedContext.comment;

import com.huh.BaekJoonSupporter.boundedContext.board.Board;
import com.huh.BaekJoonSupporter.boundedContext.board.BoardService;
import com.huh.BaekJoonSupporter.boundedContext.comment.form.CommentForm;
import com.huh.BaekJoonSupporter.boundedContext.member.Member;
import com.huh.BaekJoonSupporter.boundedContext.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
@Controller
@RequestMapping("/comment")
public class CommentController {

    private final BoardService boardService;
    private final MemberService memberService;
    private final CommentService commentService;

    @PostMapping("/create/{id}")
    public String create(
            Model model,
            @PathVariable("id") Long id,
            @Valid CommentForm form,
            BindingResult bindingResult,
            Principal principal
    ) {

        Board board = boardService.getBoard(id);
        Member member = memberService.getMember(principal.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("board", board);
            return "board/detail";
        }

        Comment comment = commentService.create(form.getComment(), board, member);
        return String.format("redirect:/board/detail/%s#comment_%s", board.getId(), comment.getId());
    }

    @GetMapping("/modify/{id}")
    public String modify(CommentForm form, @PathVariable Long id, Principal principal) {
        Comment comment = commentService.getComment(id);

        if (!comment.getMember().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }

        form.setComment(comment.getContent());
        return "수정"; // 댓글 수정 form
    }

    @PostMapping("/modify/{id}")
    public String modify(@Valid CommentForm form, BindingResult bindingResult, @PathVariable Long id, Principal principal) {

        if (bindingResult.hasErrors()) {
            return "수정"; // 댓글 수정 form
        }

        Comment comment = commentService.getComment(id);

        if (!comment.getMember().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }

        commentService.modify(comment, form.getComment());
        return String.format("redirect:/board.detail/%d", comment.getBoard().getId());
    }

    @GetMapping("/delete/{id}")
    public String delete(Principal principal, @PathVariable Long id) {
        Comment comment = commentService.getComment(id);

        if (!comment.getMember().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }

        commentService.delete(comment);
        return String.format("redirect:/board.detail/%d", comment.getBoard().getId());
    }

}
