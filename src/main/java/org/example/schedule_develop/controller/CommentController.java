package org.example.schedule_develop.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.dto.CommentRequestDto;
import org.example.schedule_develop.dto.CommentResponseDto;
import org.example.schedule_develop.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable Long scheduleId,
                                                            @SessionAttribute("LOGIN_USER") String email) {
        CommentResponseDto commentResponseDto  = commentService.createComment(scheduleId, commentRequestDto, email);
        return new ResponseEntity<>(commentResponseDto,HttpStatus.CREATED);

    }
}
