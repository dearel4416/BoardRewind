package com.example.boardrewind.user;

import com.example.boardrewind.comment.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Comment> posts;
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    public User(String nickname, String password){
        this.nickname = nickname;
        this.password = password;
    }
}
