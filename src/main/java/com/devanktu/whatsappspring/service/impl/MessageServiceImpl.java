package com.devanktu.whatsappspring.service.impl;

import com.devanktu.whatsappspring.exception.ChatException;
import com.devanktu.whatsappspring.exception.MessageExeption;
import com.devanktu.whatsappspring.exception.UserException;
import com.devanktu.whatsappspring.model.Chat;
import com.devanktu.whatsappspring.model.Message;
import com.devanktu.whatsappspring.model.User;
import com.devanktu.whatsappspring.repository.MessageRepository;
import com.devanktu.whatsappspring.request.SendMessageRequest;
import com.devanktu.whatsappspring.service.ChatService;
import com.devanktu.whatsappspring.service.MessageService;
import com.devanktu.whatsappspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;
    private UserService userService;
    private ChatService chatService;

    @Override
    public Message sendMessage(SendMessageRequest req) throws UserException, ChatException {
        User user = userService.findUserById(req.getUserId());
        Chat chat = chatService.findChatById(req.getChatId());

        Message message = new Message();
        message.setChat(chat);
        message.setUser(user);
        message.setContent(req.getContent());
        message.setTimestamp(LocalDateTime.now());
        return message;
    }

    @Override
    public List<Message> getChatMessages(Integer chatId, User reqUser) throws ChatException, UserException {
        Chat chat = chatService.findChatById(chatId);
        if (!chat.getUsers().contains(reqUser)){
            throw new UserException("You are not releted to this chat " + chat.getId());
        }
        List<Message> messages = messageRepository.findByChatId(chat.getId());

        return messages;
    }

    @Override
    public Message findMessageById(Integer messageId) throws MessageExeption {
        Optional<Message> opt = messageRepository.findById(messageId);
        if (opt.isPresent()){
            return opt.get();
        }
        throw new MessageExeption("Message not found with id " + messageId);
    }

    @Override
    public void deleteMessage(Integer messageId, User reqUser) throws MessageExeption, UserException {
        Message message = findMessageById(messageId);
        if (message.getUser().getId().equals(reqUser.getId())){
            messageRepository.deleteById(messageId);
        }
        throw new UserException("You cant deleted another user message " + reqUser.getFull_name());
    }
}
