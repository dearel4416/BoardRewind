package com.example.boardrewind.user;

import com.example.boardrewind.CommonResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDTO> signup(@Valid @RequestBody UserSignUpRequestDTO requestDTO) {
        try {
            userService.signup(requestDTO);
            return ResponseEntity.status(HttpStatus.CREATED.value())
                    .body(new CommonResponseDTO("회원 가입 성공", HttpStatus.CREATED.value()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponseDTO> login(@RequestBody UserRequestDTO requestDto, HttpServletResponse response) {
        try {
            userService.login(requestDto, response);
            return ResponseEntity.ok().body(new CommonResponseDTO("로그인 성공", HttpStatus.OK.value()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
}