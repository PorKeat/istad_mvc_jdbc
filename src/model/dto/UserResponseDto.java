package model.dto;

import java.sql.Date;

public record UserResponseDto(
        String user_name,
        String email,
        String uuid,
        Date created_date
) {
}
