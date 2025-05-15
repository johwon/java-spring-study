package com.example.myspringbootapp.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name="board")
@NoArgsConstructor
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;
}
