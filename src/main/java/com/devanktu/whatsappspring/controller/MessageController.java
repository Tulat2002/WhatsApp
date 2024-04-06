package com.devanktu.whatsappspring.controller;

import com.devanktu.whatsappspring.exception.ChatException;
import com.devanktu.whatsappspring.exception.MessageExeption;
import com.devanktu.whatsappspring.exception.UserException;
import com.devanktu.whatsappspring.model.Message;
import com.devanktu.whatsappspring.model.User;
import com.devanktu.whatsappspring.request.SendMessageRequest;
import com.devanktu.whatsappspring.response.ApiResponse;
import com.devanktu.whatsappspring.service.MessageService;
import com.devanktu.whatsappspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {

    private MessageService messageService;
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Message> sendMessageHandler(@RequestBody SendMessageRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
        User user = userService.findUserProfile(jwt);
        req.setUserId(user.getId());
        Message message = messageService.sendMessage(req);
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<Message>> getChatMessageHandler(@PathVariable Integer chatId, @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
        User user = userService.findUserProfile(jwt);
        List<Message> message = messageService.getChatMessages(chatId, user);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<ApiResponse> deleteMessageHandler(@PathVariable Integer messageId, @RequestHeader("Authorization") String jwt) throws UserException,  MessageExeption {
        User user = userService.findUserProfile(jwt);
        messageService.deleteMessage(messageId, user);
        ApiResponse res = new ApiResponse("Message deleted successfully", false);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
