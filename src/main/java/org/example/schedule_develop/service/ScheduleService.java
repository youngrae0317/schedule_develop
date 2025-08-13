package org.example.schedule_develop.service;

import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.dto.SchedulePageResponseDto;
import org.example.schedule_develop.dto.ScheduleRequestDto;
import org.example.schedule_develop.dto.ScheduleResponseDto;
import org.example.schedule_develop.dto.ScheduleUpdateRequestDto;
import org.example.schedule_develop.entity.Schedule;
import org.example.schedule_develop.entity.User;
import org.example.schedule_develop.repository.CommentRepository;
import org.example.schedule_develop.repository.ScheduleRepository;
import org.example.schedule_develop.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;


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
    public Page<SchedulePageResponseDto> findAll(int page, int size) {

        // modifiedAt 기준으로 내림차순, 페이지 번호와 페이지 크기, 정렬 기준을 담아 Pageble 객체 생성
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "modifiedAt"));

        // Repository에 pageble 객체 전달 후 Page<Schedule> 받기
        Page<Schedule> schedulePage = scheduleRepository.findAll(pageable);

        return schedulePage.map(schedule -> {
            // 각 일정(schedule)에 대한 댓글 개수를 조회
            int commentCount = commentRepository.countBySchedule_ScheduleId(schedule.getScheduleId());
            // schedule과 commentCount를 함께 넘겨 DTO 생성
            return new SchedulePageResponseDto(schedule, commentCount);
        });
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
