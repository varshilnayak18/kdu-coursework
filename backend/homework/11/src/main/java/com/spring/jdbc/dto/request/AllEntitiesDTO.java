package com.spring.jdbc.dto.request;

import com.spring.jdbc.entity.Shift;
import com.spring.jdbc.entity.ShiftType;
import com.spring.jdbc.entity.ShiftUser;
import com.spring.jdbc.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllEntitiesDTO {
    private User user;
    private Shift shift;
    private ShiftType shiftType;
    private ShiftUser shiftUser;
}
