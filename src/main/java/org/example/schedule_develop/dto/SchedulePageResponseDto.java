package org.example.schedule_develop.dto;

import lombok.Getter;
import org.example.schedule_develop.entity.Schedule;

import java.time.LocalDateTime;

@Getter
public class SchedulePageResponseDto {
    private final Long scheduleId;
    private final String title;
    private final String contents;
    private final int commentCount;
    private final String userName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public SchedulePageResponseDto(Schedule schedule, int commentCount) {
        this.scheduleId = schedule.getScheduleId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.userName = schedule.getUser().getUserName();
        this.commentCount = commentCount;
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
