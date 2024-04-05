package com.devanktu.whatsappspring.service.impl;

import com.devanktu.whatsappspring.config.TokenProvider;
import com.devanktu.whatsappspring.exception.UserException;
import com.devanktu.whatsappspring.model.User;
import com.devanktu.whatsappspring.repository.UserRepository;
import com.devanktu.whatsappspring.request.UpdateUserRequest;
import com.devanktu.whatsappspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private TokenProvider tokenProvider;

    public UserServiceImpl(UserRepository userRepository, TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public User findUserById(Integer id) throws UserException {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()){
            return opt.get();
        }
        throw new UserException("User not found exception " + id);
    }

    @Override
    public User findUserProfile(String jwt) throws UserException {
        String email = tokenProvider.getEmailFormToken(jwt);
        if (email == null){
            throw new BadCredentialsException("Recieved invalid token ---");
        }
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UserException("User not found with email " + email);
        }
        return user;
    }

    @Override
    public User updateUser(Integer userId, UpdateUserRequest req) throws UserException {
        User user = findUserById(userId);
        if (req.getFull_name() != null){
            user.setFull_name(req.getFull_name());
        }
        if (req.getProfile_picture() != null){
            user.setProfile_picture(req.getProfile_picture());
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> searchUser(String query) {
        List<User> users = userRepository.searchUser(query);
        return users;
    }
}
