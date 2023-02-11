package com.crm.operis_app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IpAddressDto {
    @JsonProperty("ip")
    String  ip;


    public IpAddressDto() {
    }

 }
