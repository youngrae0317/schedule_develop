package org.example.schedule_develop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.dto.ScheduleRequestDto;
import org.example.schedule_develop.dto.ScheduleResponseDto;
import org.example.schedule_develop.dto.ScheduleUpdateRequestDto;
import org.example.schedule_develop.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    /**
     * 일정 생성 (Lv1) -> CREATE
     **/
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(
            @Valid @RequestBody ScheduleRequestDto requestDto,
            @SessionAttribute("LOGIN_USER") String email) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.save(requestDto, email);

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    /**
     * 일정 전체 조회 (Lv1) -> READ
     **/
    @GetMapping()
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();
        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    /**
     * 일정 선택 조회 (Lv1) -> READ
     **/
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long scheduleId) {

        ScheduleResponseDto findSchedule = scheduleService.findById(scheduleId);
        return new ResponseEntity<>(findSchedule, HttpStatus.OK);

    }

    /**
     * 일정 수정 (Lv1) -> UPDATE
     **/
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@Valid @RequestBody ScheduleUpdateRequestDto requestDto,
                                                              @PathVariable Long scheduleId,
                                                              @SessionAttribute("LOGIN_USER") String email) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(scheduleId, requestDto, email);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    /**
     * 일정 삭제 (Lv1) -> DELETE
     **/
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId,
                                               @SessionAttribute("LOGIN_USER") String email) {
        scheduleService.deleteSchedule(scheduleId, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
