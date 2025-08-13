package org.example.schedule_develop.repository;

import org.example.schedule_develop.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    default Schedule findByIdOrElseThrow(Long scheduleId) {
        return findById(scheduleId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디가 존재 하지 않습니다. = " + scheduleId));
    }

    Page<Schedule> findAll(Pageable pageable);
}
