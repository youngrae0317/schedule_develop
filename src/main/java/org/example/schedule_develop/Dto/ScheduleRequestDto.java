package org.example.schedule_develop.Dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    @NotBlank
    @Size(min = 1, max = 10, message = "1자 이상 및 10자 이내여야 합니다.")
    private String title;

    private String contents;

    public ScheduleRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
