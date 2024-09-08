package com.crypto.dashboardweb.model.dto;

import com.crypto.dashboardweb.model.User;
import com.crypto.dashboardweb.model.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;

    private String firstName;
    private String lastName;

    private String username;

    private UserRole userRole;

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.setUserRole(user.getRole());
    }
}
