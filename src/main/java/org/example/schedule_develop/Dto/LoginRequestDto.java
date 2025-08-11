package org.example.schedule_develop.Dto;

import lombok.Getter;
import org.example.schedule_develop.Entity.User;

@Getter
public class LoginRequestDto {
    private String email;
    private String password;

}
