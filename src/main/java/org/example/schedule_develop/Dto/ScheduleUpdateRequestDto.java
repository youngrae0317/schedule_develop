package org.example.schedule_develop.Dto;

import lombok.Getter;

@Getter
public class ScheduleUpdateRequestDto {
    private String title;
    private String contents;

    public ScheduleUpdateRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
