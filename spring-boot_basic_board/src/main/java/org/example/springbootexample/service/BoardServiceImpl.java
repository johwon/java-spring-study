package org.example.springbootexample.service;

import jakarta.validation.Valid;
import org.example.springbootexample.dto.BoardDto;
import org.example.springbootexample.model.Board;
import org.example.springbootexample.repository.BoardRepository;
import org.example.springbootexample.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardServiceImpl(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<BoardDto.ListResponse> getBoardList(int page, int pageSize) {

        int offset = page * pageSize;

        List<Board> boards = boardRepository.findAll(offset, pageSize);

        return boards.stream()
                .map(board -> new BoardDto.ListResponse(
                        board.getId(),
                        board.getTitle(),
                        board.getUsername(),
                        board.getViewCount(),
                        board.getCreatedAt()
                )).toList();
    }

    @Override
    public int getTotalBoardCount() {
        return boardRepository.count();
    }

    @Override
    @Transactional
    public Long createBoard(BoardDto.@Valid Request request, Long userId) {
        Board board = new Board();
        board.setTitle(request.getTitle());
        board.setContent(request.getContent());
        board.setUserId(userId);

        Board saveBoard = boardRepository.save(board);

        return saveBoard.getId();
    }

    @Override
    public BoardDto.DetailResponse getBoardDetail(Long id) {
        Board board = boardRepository.findById(id);

        // 조회수
        boardRepository.incrementViewCount(id);
        board.setViewCount(board.getViewCount()+1);

        return new BoardDto.DetailResponse(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getUsername(),
                board.getUserId(),
                board.getViewCount(),
                board.getUpdatedAt(),
                board.getCreatedAt()
        );
    }

    @Override
    @Transactional
    public BoardDto.DetailResponse updateBoard(Long id, BoardDto.@Valid Request request, Long userId) {
        Board board = boardRepository.findById(id);
        board.setTitle(request.getTitle());
        board.setContent(request.getContent());

        Board updatedBoard = boardRepository.save(board);

        return new BoardDto.DetailResponse(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getUsername(),
                board.getUserId(),
                board.getViewCount(),
                board.getUpdatedAt(),
                board.getCreatedAt()
        );
    }

    @Override
    @Transactional
    public boolean deleteBoard(Long id, Long userId) {
        Board board = boardRepository.findById(id);
        // 유효성 검사
//        if(board == null){
//            throw
//        }

        return boardRepository.deleteById(id);

    }
}

