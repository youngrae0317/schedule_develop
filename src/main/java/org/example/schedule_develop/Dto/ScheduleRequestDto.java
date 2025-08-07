package org.example.schedule_develop.Dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private String userName;
    private String title;
    private String contents;

    public ScheduleRequestDto(String userName, String title, String contents) {
        this.userName = userName;
        this.title = title;
        this.contents = contents;
    }
}
