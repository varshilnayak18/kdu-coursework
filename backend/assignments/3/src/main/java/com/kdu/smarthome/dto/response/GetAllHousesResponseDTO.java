package com.kdu.smarthome.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllHousesResponseDTO {
    String message;
    String houses;
    HttpStatus httpStatus;
}
