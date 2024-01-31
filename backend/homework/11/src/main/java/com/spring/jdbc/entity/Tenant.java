package com.spring.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tenant extends BaseEntity {
    private UUID id;
    private String name;
    private String timeZone;
}
