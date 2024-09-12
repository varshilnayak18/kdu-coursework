package com.spring.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{
    private UUID id;
    private String username;
    private int loggedIn;
    private String timeZone;
    private UUID tenantId;
}
