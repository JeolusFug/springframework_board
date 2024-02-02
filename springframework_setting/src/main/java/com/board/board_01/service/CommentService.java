// 게시판용입니다. けいじばんようです
package com.board.board_01.service;

import com.board.board_01.dto.CommentDTO;
import com.board.board_01.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;



    public void save(CommentDTO commentDTO) {
        commentRepository.save(commentDTO);
    }

    public List<CommentDTO> findAll(Long boardId) {
        return commentRepository.findAll(boardId);
    }
    public void commentdelete(Long id) {
        commentRepository.commentdelete(id);
    }
}
