package com.kdu.smarthome.entity;

import com.kdu.smarthome.constants.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseOwner extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String houseOwnerId;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Override
    public String toString() {
        return "HouseOwner{" +
                "houseOwnerId=" + houseOwnerId +
                ", house=" + house +
                ", user=" + user +
                ", role=" + role +
                '}';
    }
}
