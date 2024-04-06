package com.devanktu.whatsappspring.service;

import com.devanktu.whatsappspring.exception.ChatException;
import com.devanktu.whatsappspring.exception.MessageExeption;
import com.devanktu.whatsappspring.exception.UserException;
import com.devanktu.whatsappspring.model.Message;
import com.devanktu.whatsappspring.model.User;
import com.devanktu.whatsappspring.request.SendMessageRequest;

import java.util.List;

public interface MessageService {

    Message sendMessage(SendMessageRequest req)throws UserException, ChatException;

    List<Message> getChatMessages(Integer chatId, User reqUser) throws ChatException, UserException;

    Message findMessageById(Integer messageId) throws MessageExeption;

    void deleteMessage(Integer messageId, User reqUser) throws MessageExeption, UserException;

}
