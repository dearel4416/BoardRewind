package com.example.boardrewind.user;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserSignUpRequestDTO extends UserRequestDTO {
        @Pattern(regexp = "^{4,}$")
        private String checkPassword;
}
