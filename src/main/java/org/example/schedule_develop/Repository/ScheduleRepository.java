package org.example.schedule_develop.Repository;

import org.example.schedule_develop.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
