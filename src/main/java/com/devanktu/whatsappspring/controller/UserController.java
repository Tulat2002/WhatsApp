package com.devanktu.whatsappspring.controller;

import com.devanktu.whatsappspring.exception.UserException;
import com.devanktu.whatsappspring.model.User;
import com.devanktu.whatsappspring.request.UpdateUserRequest;
import com.devanktu.whatsappspring.response.ApiResponse;
import com.devanktu.whatsappspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String token) throws UserException {
        User user = userService.findUserProfile(token);
        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{query}")
    public ResponseEntity<List<User>> searchUserHandler(@PathVariable("query") String q){
        List<User> users = userService.searchUser(q);
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateUserHandler(@RequestBody UpdateUserRequest req, @RequestHeader("Authorization") String token) throws UserException {
        User user = userService.findUserProfile(token);
        userService.updateUser(user.getId(), req);
        ApiResponse res = new ApiResponse("User updated successfully", true);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.ACCEPTED);
    }

}
