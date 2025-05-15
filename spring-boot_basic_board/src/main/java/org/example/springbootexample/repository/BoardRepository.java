package org.example.springbootexample.repository;


import org.example.springbootexample.model.Board;

import java.util.List;

public interface BoardRepository {


    List<Board> findAll(int offset, int pageSize);

    int count();

    Board save(Board board);

    Board findById(Long id);

    boolean deleteById(Long id);

    void incrementViewCount(Long id);
}

