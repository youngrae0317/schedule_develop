package org.example.schedule_develop.Controller;

import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.Dto.ScheduleRequestDto;
import org.example.schedule_develop.Dto.ScheduleResponseDto;
import org.example.schedule_develop.Service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody ScheduleRequestDto requestDto) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.save(requestDto);

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

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long scheduleId) {

        ScheduleResponseDto findSchedule = scheduleService.findById(scheduleId);
        return new ResponseEntity<>(findSchedule, HttpStatus.OK);

    }
}
