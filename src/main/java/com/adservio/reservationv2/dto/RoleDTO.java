package com.adservio.reservationv2.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleDTO implements Serializable {
    private Long id;
    private String roleName;
}
