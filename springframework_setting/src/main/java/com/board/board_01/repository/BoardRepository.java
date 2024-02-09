// 게시판용입니다. けいじばんようです
package com.board.board_01.repository;

import com.board.board_01.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final SqlSessionTemplate sql;
    // 페이징 기능이 있는 작성
    // ページングきのうがあるさくせい
    public int save(BoardDTO boardDTO) {
        return sql.insert("Board.save", boardDTO);
    }

    // 페이징 기능이 없는 작성
    // ページングきのうがないさくせい
    public int save1(BoardDTO boardDTO) {
        return sql.insert("Board.save1", boardDTO);
    }
    public List<BoardDTO> findAll() {
        return sql.selectList("Board.findAll");
    }

    public BoardDTO findById(Long id) {
        return sql.selectOne("Board.findById", id);
    }

    public void updateHits(Long id) {
        sql.update("Board.updateHits", id);
    }

    public void delete(Long id) {
        sql.delete("Board.delete", id);
    }

    public void update(BoardDTO boardDTO) {
        sql.update("Board.update", boardDTO);
    }

    public List<BoardDTO> pagingList(Map<String, Integer> pagingParams) {
        return sql.selectList("Board.pagingList", pagingParams);
    }

    public int boardCount() {
        return sql.selectOne("Board.boardCount");
    }

    // 페이징 기능이 없는 검색기능, keyword를 함께 보냄
    // ページングきのうがないけんさくきのう、keywordをいっしょにおくる
    public List<BoardDTO> findAllSearch(String keyword) {
        return sql.selectList("Board.findAllSearch", keyword);
    }
}