package com.spring.assessment.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Data
@MappedSuperclass
public class BaseEntity{
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
}

