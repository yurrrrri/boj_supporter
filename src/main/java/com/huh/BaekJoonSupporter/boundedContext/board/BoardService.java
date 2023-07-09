package com.huh.BaekJoonSupporter.boundedContext.board;

import com.huh.BaekJoonSupporter.boundedContext.category.Category;
import com.huh.BaekJoonSupporter.boundedContext.member.Member;
import com.huh.BaekJoonSupporter.boundedContext.member.MemberService;
import com.huh.BaekJoonSupporter.base.customException.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberService memberService;

    public Board getBoard(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("게시물을 찾을 수 없습니다."));
    }

    public List<Board> getBoardAll() {
        return boardRepository.findAll();
    }

    public Page<Board> getBoardAll(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));

        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return boardRepository.findAll(pageable);
    }

    public Page<Board> getBoard(int page, Category category) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));

        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return boardRepository.findByCategory(pageable, category);
    }

    @Transactional
    public Board create(String title, String post, Member member) {
        Board board = Board.create(title, post, member);
        return boardRepository.save(board);
    }

    // create in category //
    @Transactional
    public Board create(String title, String post, Member member, Category category) {
        Board board = Board.create(title, post, member, category);
        return boardRepository.save(board);
    }

    @Transactional
    public Board modify(Board board, String title, String post) {
        Board modifyBoard = board.modify(title, post);
        return boardRepository.save(modifyBoard);
    }

    @Transactional
    public void delete(Long boardId) {
        Board board = getBoard(boardId);
        Member member = memberService.getMember(board.getMember().getUsername());
        member.getBoards().remove(board);
        boardRepository.delete(board);

    }

    @Transactional
    public void addView(Board board) {
        board.addView();
    }

    @Transactional
    public void plusRecommend(Board board) {
        board.plusRecommend();
    }
}