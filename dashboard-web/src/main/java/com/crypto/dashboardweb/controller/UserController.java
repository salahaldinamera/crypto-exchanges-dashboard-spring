package com.crypto.dashboardweb.controller;

import com.crypto.dashboardweb.model.User;
import com.crypto.dashboardweb.model.dto.UserCreateDto;
import com.crypto.dashboardweb.model.dto.UserDto;
import com.crypto.dashboardweb.model.dto.UserUpdateDto;
import com.crypto.dashboardweb.service.UserService;
import com.crypto.dashboardweb.service.exceptions.AuthenticationException;
import com.crypto.dashboardweb.service.exceptions.UserException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User Controller
 * Contains users API-Endpoints
 */
@Tag(name = "User", description = "User management")
@RestController
@RequestMapping("/api/v1/users/")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Create user API-Endpoint
     * @param userCreateDto
     * @return
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreateDto userCreateDto) throws UserException {
        User user = userService.create(userCreateDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /**
     * Get users API-Endpoint
     * @return
     */
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Get user API-Endpoint
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) throws AuthenticationException, UserException {
        User user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Update user API-Endpoint
     * @param id
     * @param userUpdateDto
     * @return
     */
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserUpdateDto userUpdateDto) throws UserException {
        User user = userService.update(id, userUpdateDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Delete user API-Endpoint
     * @param id
     */
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

}
