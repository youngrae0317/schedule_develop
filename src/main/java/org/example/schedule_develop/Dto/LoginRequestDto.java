package org.example.schedule_develop.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.example.schedule_develop.Entity.User;

@Getter
public class LoginRequestDto {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, message = "8자 이상이어야 합니다.")
    private String password;

}
