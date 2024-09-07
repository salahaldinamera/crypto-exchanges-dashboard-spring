package com.crypto.dashboardweb.model.dto;

import com.crypto.dashboardweb.model.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto {
    private String firstName;
    private String lastName;
    private String username;
    private UserRole userRole;
}
