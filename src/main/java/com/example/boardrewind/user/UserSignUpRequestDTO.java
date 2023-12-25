package com.example.boardrewind.user;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserSignUpRequestDTO extends UserRequestDTO {
        @Pattern(regexp = "^[a-zA-Z0-9]{4,}$")
        private String checkPassword;
}
