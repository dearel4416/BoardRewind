package com.example.boardrewind.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService {
    private final UserRepository userRepository;
    public UserDetails getUserDetails(String username){
        User user = userRepository.findByNickname(username).orElseThrow(() -> new UsernameNotFoundException(username + "유저를 찾을 수 없습니다."));
        return new UserDetailsImpl(user);
    }
}
