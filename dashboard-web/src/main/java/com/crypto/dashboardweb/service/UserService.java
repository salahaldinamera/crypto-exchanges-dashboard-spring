package com.crypto.dashboardweb.service;

import com.crypto.dashboardweb.model.User;
import com.crypto.dashboardweb.model.dto.UserCreateDto;
import com.crypto.dashboardweb.model.dto.UserDto;
import com.crypto.dashboardweb.model.dto.UserUpdateDto;
import com.crypto.dashboardweb.service.exceptions.UserException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    /**
     * Create user service
     * @param userCreateDto
     * @return
     * @throws UserException
     */
    User create(UserCreateDto userCreateDto) throws UserException;

    /**
     * Get users srevice
     * @return
     */
    List<UserDto> getUsers();

    /**
     * Get user by id service
     * @param id
     * @return
     */
    User getById(Long id) throws UserException;

    /**
     * Get user by username
     * @param username
     * @return
     */
    User getUserByUsername(String username) throws UserException;

    /**
     * Get user by email
     * @param email
     * @return
     */
    User getUserByEmail(String email) throws UserException;

    /**
     * Update user service
     * @param id
     * @param userUpdateDto
     * @return
     */
    User update(Long id, UserUpdateDto userUpdateDto) throws UserException;

    /**
     * Update user for internal services only
     *
     * @param user
     */
    void updateInternal(User user);

    /**
     * Delete user service
     * @param id
     */
    void delete(Long id);
}
