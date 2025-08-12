package org.example.schedule_develop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedule_develop.dto.CommentRequestDto;

@Getter
@NoArgsConstructor
@Entity

public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Comment(CommentRequestDto requestDto, User user, Schedule schedule) {
        this.contents = requestDto.getContents();
        this.user = user;
        this.schedule = schedule;
    }

}
