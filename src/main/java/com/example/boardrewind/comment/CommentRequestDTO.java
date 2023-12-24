package com.example.boardrewind.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequestDTO {
    @NotBlank
    private String content;
    private boolean postLike;
}
