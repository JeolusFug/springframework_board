// 게시판용입니다. けいじばんようです
package com.board.board_01.controller;

import com.board.board_01.dto.CommentDTO;
import com.board.board_01.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/save")
    public @ResponseBody List<CommentDTO> save(HttpSession session, @ModelAttribute CommentDTO commentDTO) {
        // System.out.println("commentDTO = " + commentDTO);
        // 위에 주석처리는 댓글을 작성했을때의 테스트용입니다.
        //　うえのコメントしょりはコメントをさくせいしたときのテストようです。
        commentService.save(commentDTO);
        // 해당 게시글에 작성된 댓글 리스트를 가져옴
        // 게시글 번호가 필요 boardId 값으로 가지고 있음
        //　がいとうぶんにさくせいされたコメントリストをもってくる
        //　えきじばんばんごうがひつよう、boardIdがもっています
        List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getBoardId());
        return commentDTOList;
    }
    @PostMapping("/delete")
    public String commentdelete(@RequestParam("id") Long id) {
        commentService.commentdelete(id);
        return "redirect:/board?id=${board.id}";
    }
}
