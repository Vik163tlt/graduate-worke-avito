package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UserDetailsDto {

    private final String username;
    private final String password;
    private final Integer userId;
    private final Role role;
}
