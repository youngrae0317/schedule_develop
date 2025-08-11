package org.example.schedule_develop.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.dto.LoginRequestDto;
import org.example.schedule_develop.dto.UserRequestDto;
import org.example.schedule_develop.dto.UserResponseDto;
import org.example.schedule_develop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<UserResponseDto> save(@Valid @RequestBody UserRequestDto requestDto) {
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
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDto requestDto) {
        UserResponseDto updateUserResponseDto = userService.updateUser(id, requestDto);
        return new ResponseEntity<>(updateUserResponseDto, HttpStatus.OK);
    }

    /**
     * 유저 수정 (Lv2) -> DELETE
     **/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * 로그인 -> POST
     **/
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDto requestDto, HttpServletRequest request) {
        userService.login(requestDto);

        // 신규 세션 생성 ,JSESSIONID 쿠키
        HttpSession session = request.getSession();
        session.setAttribute("LOGIN_USER", requestDto.getEmail());

        return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
    }

    /**
     * 로그아웃 -> POST
     **/
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        // 로그인하지 않으면 HttpSession이 null로 반환된다.
        HttpSession session = request.getSession(false);
        // 세션이 존재하면 -> 로그인이 된 경우
        if (session != null) {
            session.invalidate(); // 해당 세션(데이터)을 삭제한다.
        }

        return new ResponseEntity<>("로그아웃 성공", HttpStatus.OK);
    }
}
