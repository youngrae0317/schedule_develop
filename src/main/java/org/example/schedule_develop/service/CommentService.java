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

        // 현재 로그인 중인 사용자 정보 조회
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
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto, String email) {
        // 현재 로그인 중인 사용자 정보를 조회
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("인증되지 않은 사용자입니다."));

        // 댓글 존재하는지 확인
        Comment findComment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 없습니다."));

        // 댓글 작성자와 현재 로그인한 사용자가 동일한 사용자인지 확인
        if (!findComment.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("작성자가 아니므로 댓글 수정 권한이 없습니다.");
        }

        // 조건 다 만족 시 댓글 수정
        findComment.updateComment(requestDto);

        return new CommentResponseDto(findComment);
    }

    @Transactional
    public void deleteComment(Long commentId, String email) {
        // 현재 로그인한 사용자 정보 조회
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("인증되지 않은 사용자입니다."));

        // 삭제 댓글 조회
        Comment findComment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 없습니다"));

        // 댓글 작성자와 현재 로그인한 사용자가 동일한지 확인
        if (!findComment.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("댓글을 삭제할 권한이 없습니다.");
        }

        // 댓글 삭제
        commentRepository.delete(findComment);
    }
}
