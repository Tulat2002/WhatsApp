package com.devanktu.whatsappspring.service;

import com.devanktu.whatsappspring.exception.ChatException;
import com.devanktu.whatsappspring.exception.UserException;
import com.devanktu.whatsappspring.model.Chat;
import com.devanktu.whatsappspring.model.User;
import com.devanktu.whatsappspring.request.GroupChatRequest;

import java.util.List;

public interface ChatService {

    Chat createChat(User reqUser, Integer userId2) throws UserException;

    Chat findChatById(Integer chatId) throws ChatException;

    List<Chat> findAllChatByUserId(Integer userId) throws UserException;

    Chat createGroup(GroupChatRequest req, User reqUser) throws UserException;

    Chat addUserToGroup(Integer userId, Integer chatId, User reqUser) throws UserException, ChatException;

    Chat renameGroup(Integer chatId, String groupName, User reqUser) throws ChatException, UserException;

    Chat removeFromGroup(Integer chatId, Integer userId, User reqUser) throws UserException, ChatException;

    void deleteChat(Integer chatId, Integer userId) throws ChatException, UserException;

}
