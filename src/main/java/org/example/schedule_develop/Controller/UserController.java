package org.example.schedule_develop.Controller;

import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.Dto.UserRequestDto;
import org.example.schedule_develop.Dto.UserResponseDto;
import org.example.schedule_develop.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 유저 생성 (Lv2) -> CREATE
     **/
    @PostMapping
    public ResponseEntity<UserResponseDto> save(@RequestBody UserRequestDto requestDto) {
        UserResponseDto userResponseDto = userService.saveUser(requestDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    /**
     * 유저 전체 조회 (Lv2) -> READ
     **/
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        List<UserResponseDto> userList  = userService.findAll();

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    /**
     * 유저 단일 조회 (Lv2) -> READ
     **/
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        UserResponseDto findUser = userService.findById(id);
        return new ResponseEntity<>(findUser, HttpStatus.OK);
    }

    /**
     * 유저 수정 (Lv2) -> UPDATE
     **/
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto requestDto) {
        UserResponseDto updateUserResponseDto = userService.updateUser(id, requestDto);
        return new ResponseEntity<>(updateUserResponseDto, HttpStatus.OK);
    }

}
