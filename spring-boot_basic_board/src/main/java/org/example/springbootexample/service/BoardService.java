package org.example.springbootexample.service;

import jakarta.validation.Valid;
import org.example.springbootexample.dto.BoardDto;
import java.util.List;


public interface BoardService {


    List<BoardDto.ListResponse> getBoardList(int page, int pageSize);

    int getTotalBoardCount();

    Long createBoard(BoardDto.@Valid Request request, Long userId);

    BoardDto.DetailResponse getBoardDetail(Long id);

    BoardDto.DetailResponse updateBoard(Long id, BoardDto.@Valid Request request, Long userId);

    boolean deleteBoard(Long id, Long userId);
}

