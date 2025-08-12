package org.example.schedule_develop.dto;

import lombok.Getter;
import org.example.schedule_develop.entity.Comment;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final Long id;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.contents = comment.getContents();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
