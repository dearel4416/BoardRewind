package com.example.boardrewind.comment;

import lombok.Getter;

@Getter
public class CommentResponseDTO extends CommentRequestDTO {
    private final Long id;
    private final String content;
    private final boolean postLike;
    public CommentResponseDTO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.postLike = comment.isPostLike();
    }
}
