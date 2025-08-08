package org.example.schedule_develop.Controller;

import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.Dto.UserRequestDto;
import org.example.schedule_develop.Dto.UserResponseDto;
import org.example.schedule_develop.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> save(@RequestBody UserRequestDto requestDto) {
        UserResponseDto userResponseDto = userService.saveUser(requestDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

}
