package org.example.schedule_develop.Service;

import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.Dto.ScheduleRequestDto;
import org.example.schedule_develop.Dto.ScheduleResponseDto;
import org.example.schedule_develop.Dto.ScheduleUpdateRequestDto;
import org.example.schedule_develop.Entity.Schedule;
import org.example.schedule_develop.Entity.User;
import org.example.schedule_develop.Repository.ScheduleRepository;
import org.example.schedule_develop.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;


    // 일정 생성 비즈니스 로직
    @Transactional
    public ScheduleResponseDto save(ScheduleRequestDto requestDto, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("해당 사용자가 없습니다.")
        );

        Schedule schedule = new Schedule(requestDto, user);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule);
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public ScheduleResponseDto findById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        return new ScheduleResponseDto(findSchedule);
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto requestDto, String email) {
        // 현재 로그인한 사용자 정보 가져오기
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("해당 사용자가 없습니다."));

        // 해당 일정 조회
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        // 일정 소유주 확인
        if (!findSchedule.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("해당 사용자가 아니므로 일정 수정 권한이 없습니다.");
        }

        findSchedule.scheduleUpdate(requestDto);
        return new ScheduleResponseDto(findSchedule);
    }

    @Transactional
    public void deleteSchedule(Long id, String email) {
        // 현재 로그인한 사용자 정보 가져오기
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("해당 사용자가 없습니다."));

        // 해당 일정 조회
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        // 일정 소유주 확인
        if (!findSchedule.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("해당 사용자가 아니므로 일정 수정 권한이 없습니다.");
        }

        scheduleRepository.delete(findSchedule);
    }
}
