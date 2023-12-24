package com.example.boardrewind.post;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 게시글 등록 RequestDto
@Getter
@AllArgsConstructor
public class PostRequestDTO {
    private String title;
    private String content;
}