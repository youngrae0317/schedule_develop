package org.example.schedule_develop.service;

import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.config.PasswordEncoder;
import org.example.schedule_develop.dto.LoginRequestDto;
import org.example.schedule_develop.dto.UserRequestDto;
import org.example.schedule_develop.dto.UserResponseDto;
import org.example.schedule_develop.entity.User;
import org.example.schedule_develop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDto saveUser(UserRequestDto requestDto) {
        String password = requestDto.getPassword();
        String encodePwd = passwordEncoder.encode(password);

        User user = new User(requestDto, encodePwd);
        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id) {
        User findUser = findUserById(id);
        return new UserResponseDto(findUser);
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {
        User findUser = findUserById(id);

        findUser.userUpdate(requestDto);

        return new UserResponseDto(findUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = findUserById(id);

        userRepository.delete(user);

    }

    @Transactional(readOnly = true)
    public void login(LoginRequestDto requestDto) {
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();

        // 사용자 확인 로직
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("해당 사용자가 없습니다.")
        );

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

    }

    private User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 없습니다."));
    }
}
