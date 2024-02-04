package com.kdu.smarthome.dto.response;

import com.kdu.smarthome.entity.House;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseRegisterResponseDTO {
    String message;
    House house;
    HttpStatus httpStatus;
}
