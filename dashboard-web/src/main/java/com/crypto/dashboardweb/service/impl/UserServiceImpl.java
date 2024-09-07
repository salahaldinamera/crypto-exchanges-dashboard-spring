package com.crypto.dashboardweb.service.impl;

import com.crypto.dashboardweb.dao.UserRepository;
import com.crypto.dashboardweb.model.User;
import com.crypto.dashboardweb.model.dto.UserCreateDto;
import com.crypto.dashboardweb.model.dto.UserDto;
import com.crypto.dashboardweb.model.dto.UserUpdateDto;
import com.crypto.dashboardweb.model.enums.UserRole;
import com.crypto.dashboardweb.service.UserService;
import com.crypto.dashboardweb.service.exceptions.UserException;
import com.crypto.dashboardweb.service.exceptions.enums.UserExceptionEnum;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void createInitialUser() throws UserException {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setEmail("admin@crypto.com");
        userCreateDto.setPassword("ChangeMe123@");
        userCreateDto.setFirstName("Admin");
        userCreateDto.setLastName("Crypto");
        userCreateDto.setUserRole(UserRole.ADMIN);

        if (userRepository.findByEmail(userCreateDto.getEmail()).isPresent()) {
            log.info("ADMIN USER ALREADY EXISTS");
        } else {
            User user = create(userCreateDto);

            if (user != null) {
                log.info("ADMIN USER CREATED");
            }
        }
    }

    @Override
    public User create(UserCreateDto userCreateDto) throws UserException {
        User user = new User(userCreateDto);

        if(userRepository.findByEmail(user.getEmail()).isPresent())
            throw new UserException(UserExceptionEnum.USER_EXIST);

        if (userCreateDto.getPassword() == null)
            throw new UserException(UserExceptionEnum.PASSWORD_NOT_PROVIDED);

        user.setDefaultPasswordChanged(false);
        user.setCreationDate(Calendar.getInstance().getTime());
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> userDtos.add(new UserDto(user)));
        return userDtos;
    }

    @Override
    public User getById(Long id) throws UserException {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()) throw new UserException(UserExceptionEnum.USER_NOT_FOUND);
        return userOptional.get();
    }

    @Override
    public User getUserByUsername(String username) throws UserException {
        return getUserByEmail(username);
    }

    @Override
    public User getUserByEmail(String email) throws UserException {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()) throw new UserException(UserExceptionEnum.USER_NOT_FOUND);
        return user.get();    }

    @Override
    public User update(Long id, UserUpdateDto userUpdateDto) throws UserException {
        if(userUpdateDto == null) throw new UserException(UserExceptionEnum.USER_IS_NULL);
        User user = getById(id);
        user.update(userUpdateDto);
        return userRepository.save(user);
    }

    @Override
    public void updateInternal(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user;
        user = userRepository.findByEmail(username);
        if(user.isEmpty()) throw new UsernameNotFoundException("User not found with username : " + username);
        return user.get();
    }
}
