package com.example.myspringbootapp.service;

import com.example.myspringbootapp.jpa.repository.BoardRepository;
import com.example.myspringbootapp.model.Board;
import com.example.myspringbootapp.model.BoardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BoardService {

    private final Map<Long, Board> boards = new HashMap<>();
    private Long idCounter = 1L;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BoardRepository boardRepository;

    private final RowMapper<Board> boardRowMapper = (rs, rowNum) -> {
        Board board = new Board();
        board.setId(rs.getLong("id"));
        board.setTitle(rs.getString("title"));
        board.setContent(rs.getString("content"));
        return board;
    };

    public Board create(Board board) {
        board.updateId(board.getId());
        boards.put(board.getId(), board);
        return board;
    }

    public Board findById(Long id) {
        String sql = "select b.* from boards b where b.id = ?";
        Board board = jdbcTemplate.queryForObject(sql, boardRowMapper, id);
        return board;
    }

    // jpa
    public BoardEntity findByIdWithJpa(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(()->new RuntimeException("데이터가 없습니다."));
    }
}
