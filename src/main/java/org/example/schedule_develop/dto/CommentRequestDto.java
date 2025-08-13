package org.example.schedule_develop.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String contents;

    public CommentRequestDto(String contents) {
        this.contents = contents;
    }
}
