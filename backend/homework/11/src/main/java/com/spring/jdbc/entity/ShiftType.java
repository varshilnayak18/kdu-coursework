package com.spring.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiftType extends BaseEntity {
    private UUID id;
    private String uniqueName;
    private String description;
    private boolean active;
    private String timeZone;
    private UUID tenantId;
}

