package com.example.boardrewind.user;


import com.example.boardrewind.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public void signup(UserSignUpRequestDTO infoRequestDTO) {
        String nickname = infoRequestDTO.getNickname();
        String password = infoRequestDTO.getPassword();
        String checkPassword = infoRequestDTO.getCheckPassword();

        if (password.contains(nickname)) {
            throw new IllegalArgumentException("회원 가입 실패.");
        }

        if (!password.equals(checkPassword)) {
            throw new IllegalArgumentException("비밀번호 확인 불일치");
        }

        checkDuplicatedUsername(nickname);

        User user = new User(nickname, password);
        userRepository.save(user);
    }

    public void checkDuplicatedUsername(String nickname){
        if (userRepository.findByNickname(nickname).isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        }
    }

    public void login(UserRequestDTO requestDTO, HttpServletResponse response) {
        String username = requestDTO.getNickname();
        String password = requestDTO.getPassword();

        User user = userRepository.findByNickname(username).orElseThrow(() -> new IllegalArgumentException("닉네임 또는 패스워드를 확인해주세요."));

        if (password.equals(user.getPassword())) {
            throw new IllegalArgumentException("닉네임 또는 패스워드를 확인해주세요.");
        }

        response.setHeader(JwtUtil.AUTH_HEADER, jwtUtil.createToken(requestDTO.getNickname()));
    }
}
