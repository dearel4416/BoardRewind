package com.example.boardrewind.comment;

import com.example.boardrewind.post.Post;
import com.example.boardrewind.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "comment")
@NoArgsConstructor
public class Comment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String content;
    @Column
    private boolean postLike;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(CommentRequestDTO requestDTO, User user, Post post) {
        this.content = requestDTO.getContent();
        this.postLike = requestDTO.isPostLike();
        this.user = user;
        this.post = post;
    }
}
