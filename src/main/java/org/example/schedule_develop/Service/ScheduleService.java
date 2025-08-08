package org.example.schedule_develop.Service;

import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.Dto.ScheduleRequestDto;
import org.example.schedule_develop.Dto.ScheduleResponseDto;
import org.example.schedule_develop.Dto.ScheduleUpdateRequestDto;
import org.example.schedule_develop.Entity.Schedule;
import org.example.schedule_develop.Entity.User;
import org.example.schedule_develop.Repository.ScheduleRepository;
import org.example.schedule_develop.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // 일정 생성 비즈니스 로직
    public ScheduleResponseDto save(ScheduleRequestDto requestDto) {
        User user = userRepository.findByIdOrElseThrow(requestDto.getId());

        Schedule schedule = new Schedule(requestDto, user);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule);
    }

    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::new)
                .toList();
    }

    public ScheduleResponseDto findById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        return new ScheduleResponseDto(findSchedule);
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto requestDto) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        findSchedule.scheduleUpdate(requestDto);

        return new ScheduleResponseDto(findSchedule);
    }

    public void deleteSchedule(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        scheduleRepository.delete(findSchedule);
    }
}
