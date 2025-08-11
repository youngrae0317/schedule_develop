package org.example.schedule_develop.Service;

import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.Dto.LoginRequestDto;
import org.example.schedule_develop.Dto.ScheduleResponseDto;
import org.example.schedule_develop.Dto.UserRequestDto;
import org.example.schedule_develop.Dto.UserResponseDto;
import org.example.schedule_develop.Entity.User;
import org.example.schedule_develop.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto saveUser(UserRequestDto requestDto) {
        User user = new User(requestDto);
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
        User findUser = userRepository.findByIdOrElseThrow(id);
        return new UserResponseDto(findUser);
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        findUser.userUpdate(requestDto);

        return new UserResponseDto(findUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user =  userRepository.findByIdOrElseThrow(id);

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

        if (!user.getPassword().equals(password)) {
            throw new  IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

    }
}
