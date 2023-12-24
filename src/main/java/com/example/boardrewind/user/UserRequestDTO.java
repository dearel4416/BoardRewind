package com.example.boardrewind.user;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserRequestDTO {
    @Pattern(regexp = "^[a-z0-9]{3,}$")
    private String nickname;
    @Pattern(regexp = "^{4,}$")
    private String password;
}
