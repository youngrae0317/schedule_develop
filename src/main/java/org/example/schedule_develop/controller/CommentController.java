package org.example.schedule_develop.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.dto.CommentRequestDto;
import org.example.schedule_develop.dto.CommentResponseDto;
import org.example.schedule_develop.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    /**
     * 댓글 생성 (Lv7) -> CREATE
     **/
    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable Long scheduleId,
                                                            @SessionAttribute("LOGIN_USER") String email) {
        CommentResponseDto commentResponseDto  = commentService.createComment(scheduleId, commentRequestDto, email);
        return new ResponseEntity<>(commentResponseDto,HttpStatus.CREATED);
    }

    /**
     * 해당 일정 댓글 읽기 (Lv7) -> READ
     **/
    @GetMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long scheduleId) {
        List<CommentResponseDto> commentResponseDtoList = commentService.getComments(scheduleId);
        return new ResponseEntity<>(commentResponseDtoList,HttpStatus.OK);
    }
}
