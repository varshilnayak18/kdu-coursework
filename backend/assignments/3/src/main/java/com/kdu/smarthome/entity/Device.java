package com.kdu.smarthome.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Device extends BaseEntity {
    @Id
    @JsonProperty("kickston_id")
    private String kickstonId;
    @JsonProperty("device_username")
    private String deviceUsername;
    @JsonProperty("device_password")
    private String devicePassword;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;
}
