package org.example.schedule_develop.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.Dto.ScheduleRequestDto;
import org.example.schedule_develop.Dto.ScheduleUpdateRequestDto;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    private String userName;
    private String title;
    private String contents;

    public Schedule(String title, String contents, String userName) {
        this.title = title;
        this.contents = contents;
        this.userName = userName;
    }

    public Schedule(ScheduleRequestDto requestDto) {
        this.userName = requestDto.getUserName();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void scheduleUpdate(ScheduleUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

}
