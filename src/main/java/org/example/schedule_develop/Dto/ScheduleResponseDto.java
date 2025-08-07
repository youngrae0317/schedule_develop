package org.example.schedule_develop.Dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private final Long id;
    private final String title;
    private final String contents;
    private final String userName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    public ScheduleResponseDto(Long id, String title, String contents, String userName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.userName = userName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
