package org.example.schedule_develop.Service;

import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.Dto.ScheduleResponseDto;
import org.example.schedule_develop.Dto.UserRequestDto;
import org.example.schedule_develop.Dto.UserResponseDto;
import org.example.schedule_develop.Entity.User;
import org.example.schedule_develop.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto saveUser(UserRequestDto requestDto) {
        User user = new User(requestDto);
        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser);
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::new)
                .toList();
    }

    public UserResponseDto findById(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        return new UserResponseDto(findUser);
    }

    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        findUser.userUpdate(requestDto);

        return new UserResponseDto(findUser);
    }

    public void deleteUser(Long id) {
        User user =  userRepository.findByIdOrElseThrow(id);

        userRepository.delete(user);

    }
}
