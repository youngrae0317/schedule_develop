package org.example.schedule_develop.service;

import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.dto.CommentRequestDto;
import org.example.schedule_develop.dto.CommentResponseDto;
import org.example.schedule_develop.entity.Comment;
import org.example.schedule_develop.entity.Schedule;
import org.example.schedule_develop.entity.User;
import org.example.schedule_develop.repository.CommentRepository;
import org.example.schedule_develop.repository.ScheduleRepository;
import org.example.schedule_develop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CommentResponseDto createComment(Long scheduleId, CommentRequestDto requestDto, String email) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(scheduleId);
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("해당 사용자가 없습니다.")
        );

        Comment comment = new Comment(requestDto,  user, schedule);
        Comment saveComment = commentRepository.save(comment);

        return new CommentResponseDto(saveComment);
    }
}
