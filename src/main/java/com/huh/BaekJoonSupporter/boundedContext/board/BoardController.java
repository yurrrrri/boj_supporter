package com.huh.BaekJoonSupporter.boundedContext.board;

import com.huh.BaekJoonSupporter.boundedContext.board.form.BoardCreateForm;
import com.huh.BaekJoonSupporter.boundedContext.category.Category;
import com.huh.BaekJoonSupporter.boundedContext.category.CategoryService;
import com.huh.BaekJoonSupporter.boundedContext.member.Member;
import com.huh.BaekJoonSupporter.boundedContext.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;
    private final CategoryService categoryService;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/list")
    public String showList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "-1") Long categoryId,
            Model model
    ) {

        Page<Board> paging;

        if (categoryId == -1) {
            paging = boardService.getBoardAll(page);
            model.addAttribute("id", "-1");
        } else {
            Category category = categoryService.getCategory(categoryId);
            paging = boardService.getBoard(page, category);
            model.addAttribute("category", category.getName());
        }
        model.addAttribute("id", categoryId);
        model.addAttribute("paging", paging);
        return "/board/boardList";
    }


    @GetMapping("/create")
    public String showCreateForm(BoardCreateForm form, Model model) {
        List<Category> categories = categoryService.getCategoryAll();
        model.addAttribute("categories", categories);
        return "/board/create";
    }

    @PostMapping("/create")
    public String showCreateForm(BoardCreateForm form, BindingResult bindingResult, Principal principal) {
        Member member = memberService.getMember(principal.getName());

        if (form.getCategory().equals(""))
            boardService.create(form.getTitle(), form.getPost(), member);
        else {
            Category category = categoryService.getCategory(form.getCategory());
            boardService.create(form.getTitle(), form.getPost(), member, category);
        }
        return "redirect:/board/list";
    }

    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable Long id, Model model) {
        Board board = boardService.getBoard(id);
        boardService.addView(board);

        model.addAttribute("board", board);
        return "board/detail";
    }

    @GetMapping("/update/{id}")
    public String showUpdate(@PathVariable Long id, BoardCreateForm form, Principal principal) {
        Board board = boardService.getBoard(id);

        if (!board.getMember().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }

        form.setTitle(board.getTitle());
        form.setPost(board.getPost());
        return "/board/create";
    }

    @PostMapping("/update/{id}")
    public String showUpdate(@PathVariable Long id, BoardCreateForm form) {
        Board board = boardService.getBoard(id);
        boardService.modify(board, form.getTitle(), form.getPost());
        return String.format("redirect:/board/detail/%d", id);
    }


    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable Long id, Principal principal) {
        Board board = boardService.getBoard(id);

        if (!board.getMember().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }

        Category category = categoryService.getCategory(board.getCategory().getId());
        boardService.delete(id);
        return "redirect:/board/list?id=" + category.getId();
    }

}