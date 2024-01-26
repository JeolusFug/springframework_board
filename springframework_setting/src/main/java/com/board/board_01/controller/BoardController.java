package com.board.board_01.controller;

import com.board.board_01.dto.BoardDTO;
import com.board.board_01.dto.CommentDTO;
import com.board.board_01.dto.PageDTO;
import com.board.board_01.service.BoardService;
import com.board.board_01.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        int saveResult = boardService.save(boardDTO);
        if (saveResult > 0) {
            return "redirect:/board/paging";
        } else {
            return "save";
        }
    }


    @GetMapping("/")
    public String findAll(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }

    @GetMapping
    public String findById(@RequestParam("id") Long id,
                           @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                           Model model) {
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        model.addAttribute("page", page);
        List<CommentDTO> commentDTOList = commentService.findAll(id);
        model.addAttribute("commentList", commentDTOList);
        return "detail";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        boardService.delete(id);
        return "redirect:/board/paging";
    }

    @GetMapping("/update")
    public String updateForm(@RequestParam("id") Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        boardService.update(boardDTO);
       BoardDTO dto = boardService.findById(boardDTO.getId());
       model.addAttribute("board", dto);
       return "detail";
 //      return "redirect:/board?id="+boardDTO.getId();
    }
    
    // 처음 페이지 요청은 1페이지를 보여줌
    // required = false 는 필수가 아니라는 뜻
    // /board/paging?page=2 라고오면 page 변수에는 2가 들어가고
    // /board/paging?page 가 오면 page 변수에는 defaultValue = "1" 에 설정한 것 처럼 1이 들어옴
    // さいしょのページのリクエストは1ページをひょうじ
    // required = falseはひっすではないといういみ
    // /board/paging?page=2がくるとpageへんすうには２がはいって
    // /board/paging?pageがくろとpageへんすうひはdefaultValue = "1"にせっていしたように１がはいる

    @GetMapping("/paging")
    public String paging(Model model,
                         @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
//        System.out.println("page = " + page);
        // 해당 페이지에서 보여줄 글 목록
        //　がいとうページでひょうじするぶんのリスト、もくろく
        List<BoardDTO> pagingList = boardService.pagingList(page);
//        System.out.println("pagingList = " + pagingList);
        PageDTO pageDTO = boardService.pagingParam(page);
        model.addAttribute("boardList", pagingList);
        model.addAttribute("paging", pageDTO);
        return "paging";
    }
}
