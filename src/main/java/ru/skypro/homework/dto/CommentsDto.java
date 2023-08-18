package ru.skypro.homework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CommentsDto {
    Integer count;
    List<CommentDto> results;

    public CommentsDto(int size, List<CommentDto> commentsDtoList) {
    }
}


