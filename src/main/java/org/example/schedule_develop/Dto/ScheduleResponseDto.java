package org.example.schedule_develop.Dto;

import lombok.Getter;
import org.example.schedule_develop.Entity.Schedule;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private final Long id;
    private final String title;
    private final String contents;
    private final String userName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.userName = schedule.getUserName();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
