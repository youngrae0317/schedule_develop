package org.example.schedule_develop.repository;

import org.example.schedule_develop.entity.Comment;
import org.example.schedule_develop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllBySchedule_ScheduleId(Long scheduleId);

    Optional<Comment> findById(Long id);

    int countBySchedule_ScheduleId(Long scheduleId);
}
