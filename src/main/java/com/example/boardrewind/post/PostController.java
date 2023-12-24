package com.example.boardrewind.post;

import com.example.boardrewind.CommonResponseDTO;
import com.example.boardrewind.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @GetMapping("/list")
    public ResponseEntity<List<PostResponseDTO>> getAllPostList(){
        List<PostResponseDTO> postList = postService.getPosts();
        return ResponseEntity.ok().body(postList);
    }

    @PostMapping("/post")
    public ResponseEntity<CommonResponseDTO> post(@RequestBody PostRequestDTO requestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails){
        try {
            postService.post(requestDTO, userDetails);
            return ResponseEntity.status(HttpStatus.CREATED.value()).body(new CommonResponseDTO("게시 성공", HttpStatus.CREATED.value()));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(new CommonResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    public ResponseEntity<CommonResponseDTO> getPost(@PathVariable Long postId){
        try {
            PostResponseDTO postResponseDTO = postService.getPost(postId);
            return ResponseEntity.ok().body(postResponseDTO);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(new CommonResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<CommonResponseDTO> putPost(@PathVariable Long postId, @RequestBody PostRequestDTO requestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails){
        try {
            postService.updatePost(postId, requestDTO, userDetails);
            return ResponseEntity.ok().body(new CommonResponseDTO("게시물 수정 성공", HttpStatus.CREATED.value()));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(new CommonResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<CommonResponseDTO> deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        try {
            postService.deletePost(postId, userDetails);
            return ResponseEntity.ok().body(new CommonResponseDTO("게시물 삭제 성공", HttpStatus.CREATED.value()));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(new CommonResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
}