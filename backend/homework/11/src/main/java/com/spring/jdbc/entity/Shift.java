package com.spring.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shift extends BaseEntity {
    private UUID id;
    private UUID shiftTypeId;
    private String name;
    private Date dateStart;
    private Date dateEnd;
    private Time timeStart;
    private Time timeEnd;
    private String timeZone;
    private UUID tenantId;
}
