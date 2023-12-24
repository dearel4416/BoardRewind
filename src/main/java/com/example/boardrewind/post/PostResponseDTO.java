package com.example.boardrewind.post;

import com.example.boardrewind.CommonResponseDTO;
import java.time.LocalDateTime;
public class PostResponseDTO extends CommonResponseDTO {
    String title;

    String nickname;
    String content;
    LocalDateTime createdAt;
    public PostResponseDTO(Post savePost) {
          this.title = savePost.getTitle();
          this.nickname = savePost.getUser().getNickname();
          this.content = savePost.getContent();
          this.createdAt = savePost.getCreatedAt();
    }
}

