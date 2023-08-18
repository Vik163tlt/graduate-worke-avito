package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class AdsDto {
    Integer count;
    List<AdDto> results;

    public AdsDto(Integer count, List<AdDto> results) {
        this.count = count;
        this.results = results;
    }
}
