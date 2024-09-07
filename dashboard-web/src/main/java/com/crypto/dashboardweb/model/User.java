package com.crypto.dashboardweb.model;

import com.crypto.dashboardweb.model.dto.UserCreateDto;
import com.crypto.dashboardweb.model.dto.UserUpdateDto;
import com.crypto.dashboardweb.model.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User Model
 * Contains the user info to be stored in the DB
 */
@Entity(name = "system_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * @see UserRole for detailed info about each role
     */
    @Enumerated(EnumType.STRING)
    private UserRole role;

    private boolean defaultPasswordChanged;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AccessToken accessToken;

    private Date creationDate;

    public User() {
    }

    public User(UserCreateDto userCreateDto) {
        this.firstName = userCreateDto.getFirstName();
        this.lastName = userCreateDto.getLastName();
        this.email = userCreateDto.getEmail();
        this.password = userCreateDto.getPassword();
        this.role = userCreateDto.getUserRole();
        this.defaultPasswordChanged = false;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<UserRole> authorities = Arrays.asList(role);
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isDefaultPasswordChanged() {
        return defaultPasswordChanged;
    }

    public void setDefaultPasswordChanged(boolean defaultPaswordChanged) {
        this.defaultPasswordChanged = defaultPaswordChanged;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date registrationDate) {
        this.creationDate = registrationDate;
    }

    public void update(UserUpdateDto userUpdateDto) {
        if(userUpdateDto.getFirstName() != null) this.setFirstName(userUpdateDto.getFirstName());
        if(userUpdateDto.getLastName() != null) this.setLastName(userUpdateDto.getLastName());
        if(userUpdateDto.getEmail() != null) this.setEmail(userUpdateDto.getEmail());
        if(userUpdateDto.getUserRole() != null) this.setRole(userUpdateDto.getUserRole());
    }
}
