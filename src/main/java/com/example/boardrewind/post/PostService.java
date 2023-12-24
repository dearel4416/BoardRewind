package com.example.boardrewind.post;

import com.example.boardrewind.user.User;
import com.example.boardrewind.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    // 게시글 전체 조회
    public List<PostResponseDTO> getPosts(){
        List<Post> posts = postRepository.findAll();
        List<PostResponseDTO> postList = new ArrayList<>();
        if (posts.isEmpty()){
            return postList;
        }
        posts.forEach((post) -> postList.add(new PostResponseDTO(post)));
        postList.sort((t1, t2) -> t2.createdAt.compareTo(t1.createdAt));
        return postList;
    }

    // 게시글 등록
    public void post(PostRequestDTO requestDTO, UserDetailsImpl userDetails) {
        Post post = new Post(requestDTO, userDetails.getUser());
        postRepository.save(post);
    }

    // 게시글 조회
    public PostResponseDTO getPost(Long postId) {
        Post post = getPostEntity(postId);
        return new PostResponseDTO(post);
    }

    // 게시글 수정
    @Transactional
    public void updatePost(Long postId, PostRequestDTO requestDto, UserDetailsImpl userDetails) {
        Post post = getPostEntity(postId);

        checkUserPermission(userDetails.getUser(), post);

        post.setTitle(requestDto.getTitle());
        post.setContent(requestDto.getContent());
    }

    // 게시글 삭제
    @Transactional
    public void deletePost(Long postId, UserDetailsImpl userDetails) {
        Post post = getPostEntity(postId);
        checkUserPermission(userDetails.getUser(), post);
        postRepository.delete(post);
    }

    // 게시글 Id 찾기
    public Post getPostEntity(Long postId){
        return postRepository.findById(postId).orElseThrow(() -> new NullPointerException("해당 게시글을 찾을 수 없습니다."));
    }

    // 토큰의 유저정보(user)와  엔티티의 유저정보를 비교하여 동일인인지 검증
    private void checkUserPermission(User user, Post post) {
        if (!user.getId().equals(post.getUser().getId())) {
            throw new AccessDeniedException("권한이 없습니다.");  // 403을 리턴하는 에러
        }
    }
}


