package model.dto;

import java.sql.Date;

public record UserCreateDto(
        String user_name,
        String email,
        String password,
        Date created_date
) {
}
