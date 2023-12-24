package com.example.boardrewind.comment;

import com.example.boardrewind.CommonResponseDTO;
import com.example.boardrewind.post.PostController;
import com.example.boardrewind.post.PostResponseDTO;
import com.example.boardrewind.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/posts")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;
    private final PostController postController;

    @PostMapping("/{postId}/comment")
    public ResponseEntity<CommonResponseDTO> createComment(@PathVariable Long postId, @RequestBody CommentRequestDTO requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            commentService.createComment(postId, requestDto, userDetails.getUser());
            return ResponseEntity.status(HttpStatus.CREATED.value())
                    .body(new CommonResponseDTO("댓글 등록 성공", HttpStatus.CREATED.value()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CommonResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommonResponseDTO> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDTO requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            commentService.updateComment(commentId, requestDto, userDetails.getUser());
            return ResponseEntity.status(HttpStatus.CREATED.value())
                    .body(new CommonResponseDTO("댓글 수정 성공", HttpStatus.CREATED.value()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CommonResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }

    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<CommonResponseDTO> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            commentService.deleteComment(commentId, userDetails.getUser());
            return ResponseEntity.status(HttpStatus.CREATED.value())
                    .body(new CommonResponseDTO("댓글 삭제 성공", HttpStatus.CREATED.value()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CommonResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponseDTO>> getAllPostList(@PathVariable Long postId){
        postController.getPost(postId);
        List<CommentResponseDTO> commentList = commentService.getPosts();
        return ResponseEntity.ok().body(commentList);
    }
}
