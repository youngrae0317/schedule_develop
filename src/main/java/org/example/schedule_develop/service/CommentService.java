package org.example.schedule_develop.service;

import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.dto.CommentRequestDto;
import org.example.schedule_develop.dto.CommentResponseDto;
import org.example.schedule_develop.dto.ScheduleResponseDto;
import org.example.schedule_develop.entity.Comment;
import org.example.schedule_develop.entity.Schedule;
import org.example.schedule_develop.entity.User;
import org.example.schedule_develop.repository.CommentRepository;
import org.example.schedule_develop.repository.ScheduleRepository;
import org.example.schedule_develop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CommentResponseDto createComment(Long scheduleId, CommentRequestDto requestDto, String email) {
        // 일정 존재 하는지 확인
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(scheduleId);

        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("해당 사용자가 없습니다.")
        );

        Comment comment = new Comment(requestDto,  user, schedule);
        Comment saveComment = commentRepository.save(comment);

        return new CommentResponseDto(saveComment);
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> getComments(Long scheduleId) {
        // 일정 존재하는지 확인
        scheduleRepository.findByIdOrElseThrow(scheduleId);

        // 댓글 목록 조회 및 DTO 변환 후 반환
        return commentRepository.findAllBySchedule_ScheduleId(scheduleId)
                .stream()
                .map(CommentResponseDto::new)
                .toList();

    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto) {
        // 댓글 존재하는지 확인
        Comment findComment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 없습니다."));

        // 존재하면 수정
        findComment.updateComment(requestDto);

        return new CommentResponseDto(findComment);
    }
}
