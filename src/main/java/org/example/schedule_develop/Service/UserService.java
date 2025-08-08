package org.example.schedule_develop.Service;

import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.Dto.UserRequestDto;
import org.example.schedule_develop.Dto.UserResponseDto;
import org.example.schedule_develop.Entity.User;
import org.example.schedule_develop.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto saveUser(UserRequestDto requestDto) {
        User user = new User(requestDto);
        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser);
    }

}
