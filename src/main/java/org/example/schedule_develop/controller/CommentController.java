package org.example.schedule_develop.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.dto.CommentRequestDto;
import org.example.schedule_develop.dto.CommentResponseDto;
import org.example.schedule_develop.entity.Comment;
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

    /**
     * 댓글 수정 (Lv7) -> PUT
     **/
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponseDto>  updateComment(@RequestBody CommentRequestDto commentRequestDto,
                                                             @PathVariable Long commentId,
                                                             @SessionAttribute("LOGIN_USER") String email) {
        CommentResponseDto commentResponseDto = commentService.updateComment(commentId, commentRequestDto, email);
        return new ResponseEntity<>(commentResponseDto,HttpStatus.OK);
    }

    /**
     * 댓글 삭제 (Lv7) -> PUT
     **/
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId,
                                              @SessionAttribute("LOGIN_USER") String email) {
        commentService.deleteComment(commentId, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
