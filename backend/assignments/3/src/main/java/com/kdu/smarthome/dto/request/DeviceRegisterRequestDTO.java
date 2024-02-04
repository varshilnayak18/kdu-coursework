package com.kdu.smarthome.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceRegisterRequestDTO {
    @JsonProperty("kickston_id")
    private String kickstonId;
    @JsonProperty("device_username")
    private String deviceUsername;
    @JsonProperty("device_password")
    private String devicePassword;
}
