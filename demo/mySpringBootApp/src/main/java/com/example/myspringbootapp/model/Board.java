package com.example.myspringbootapp.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Board {
    private Long id;
    private String title;
    private String content;
    private String writer;

    public Long updateId(Long id) {
        return this.id = id;
    }
}

// ORM : 데이터베이스와 자바 코드간의 매핑이 되는 기술 (매핑되는 객체를 ENTITY)
