package com.example.myspringbootapp.controller;

import com.example.myspringbootapp.model.Board;
import com.example.myspringbootapp.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    // 게시글 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoard(@PathVariable Long id){
        Board board =  boardService.findById(id);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody Board board){
        Board createdBoard = boardService.create(board);
        return ResponseEntity.ok(createdBoard);
    }

}
