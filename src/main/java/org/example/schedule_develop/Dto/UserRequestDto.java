package org.example.schedule_develop.Dto;

import lombok.Getter;

@Getter
public class UserRequestDto {
    private String userName;
    private String email;
    private String password;

    public UserRequestDto(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }
}
