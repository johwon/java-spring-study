package org.example.springbootexample.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;


public class BoardDto {

    @Data
    public static class Request{
        @NotBlank(message = "제목은 필수입니다.")
        @Size(max=200,message="제목은 최대 200글자까지 입력 가능합니다.")
        private String title;

        @NotBlank(message = "내용은 필수입니다.")
        private String content;
    }

    @Data
    // 게시글 목록 응답 DTO
    public static class ListResponse{
        private Long id;
        private String title;
        private String username;
        private int viewCount;
        private String createdAt;

        public ListResponse(Long id, String title, String username, int viewCount, LocalDateTime createdAt) {
            this.id = id;
            this.title = title;
            this.username = username;
            this.viewCount = viewCount;
            this.createdAt = LocalDateTime.now().toString();
        }
    }

    @Data
    public static class DetailResponse{
        private Long id;
        private String title;
        private String content;
        private String username;
        private Long userId;
        private int viewCount;
        private String createdAt;
        private String updatedAt;

        public DetailResponse(Long id, String title, String content, String username, Long userId, int viewCount, String createdAt, String updatedAt) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.username = username;
            this.userId = userId;
            this.viewCount = viewCount;
            this.createdAt = LocalDateTime.now().toString();
            this.updatedAt = LocalDateTime.now().toString();
        }

        public DetailResponse(Long id, String title, String content, String username, Long userId, int viewCount, LocalDateTime updatedAt, LocalDateTime createdAt) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.username = username;
            this.userId = userId;
            this.viewCount = viewCount;
            this.createdAt = LocalDateTime.now().toString();
            this.updatedAt = LocalDateTime.now().toString();
        }
    }

}
