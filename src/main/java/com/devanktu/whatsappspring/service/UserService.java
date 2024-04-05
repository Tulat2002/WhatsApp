package com.devanktu.whatsappspring.service;

import com.devanktu.whatsappspring.exception.UserException;
import com.devanktu.whatsappspring.model.User;
import com.devanktu.whatsappspring.request.UpdateUserRequest;
import jdk.jshell.spi.ExecutionControl;

import java.util.List;

public interface UserService {

    public User findUserById(Integer id) throws UserException;

    public User findUserProfile(String jwt) throws UserException;

    public User updateUser(Integer userId, UpdateUserRequest req) throws UserException;

    public List<User> searchUser(String query);

}
