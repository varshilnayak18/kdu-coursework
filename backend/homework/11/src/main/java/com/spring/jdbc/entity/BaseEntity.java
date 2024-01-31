package com.spring.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
}
