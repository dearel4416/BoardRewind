package com.example.boardrewind.comment;

import com.example.boardrewind.post.Post;
import com.example.boardrewind.post.PostService;
import com.example.boardrewind.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.access.AccessDeniedException;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;

    public void createComment(Long postId, CommentRequestDTO requestDto, User user) {
        Post post = postService.getPostEntity(postId);

        Comment comment = new Comment(requestDto, user, post);
        commentRepository.save(comment);
    }

    public void updateComment(Long commentId, CommentRequestDTO requestDto, User user) {
        Comment comment = checkCommentId(commentId);

        checkUserPermission(user, comment);

        comment.setContent(requestDto.getContent());
    }

    public void deleteComment(Long commentId, User user) {
        Comment comment = checkCommentId(commentId);

        checkUserPermission(user, comment);
        commentRepository.delete(comment);
    }

    // 에러처리 1. 로그찍고  2. 에러를 다시 던진다.  3. trycatch 로 컨트롤러에서 받고  리스폰스엔티티를 활용하여 응답한다.  일괄처리도 알아보기
    private Comment checkCommentId(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다."));
    }

    // 토큰의 유저정보(user)와  엔티티의 유저정보를 비교하여 동일인인지 검증
    private void checkUserPermission(User user, Comment comment) {
        if (!user.getId().equals(comment.getUser().getId())) {
            throw new AccessDeniedException("권한이 없습니다.");  // 403을 리턴하는 에러
        }
    }

    public List<CommentResponseDTO> getPosts() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentResponseDTO> commentList = new ArrayList<>();
        if (comments.isEmpty()){
            return commentList;
        }
        comments.forEach((comment) -> commentList.add(new CommentResponseDTO(comment)));
        return commentList;
    }
}

