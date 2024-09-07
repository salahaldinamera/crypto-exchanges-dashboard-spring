package com.crypto.dashboardweb.service.impl;

import com.crypto.dashboardweb.dao.UserRepository;
import com.crypto.dashboardweb.model.User;
import com.crypto.dashboardweb.model.dto.UserCreateDto;
import com.crypto.dashboardweb.model.dto.UserDto;
import com.crypto.dashboardweb.model.dto.UserUpdateDto;
import com.crypto.dashboardweb.service.UserService;
import com.crypto.dashboardweb.service.exceptions.UserException;
import com.crypto.dashboardweb.service.exceptions.enums.UserExceptionEnum;
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

    @Override
    public User create(UserCreateDto userCreateDto) throws UserException {
        User user = new User(userCreateDto);

        if(userRepository.findByUsername(user.getUsername()).isPresent())
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
        return getUserByUsername(username);
    }

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
        user = userRepository.findByUsername(username);
        if(user.isEmpty()) throw new UsernameNotFoundException("User not found with username : " + username);
        return user.get();
    }
}
