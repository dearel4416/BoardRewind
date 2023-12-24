package com.example.boardrewind.user;

import com.example.boardrewind.CommonResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserResponseDTO extends CommonResponseDTO {
    private String nickname;
}
