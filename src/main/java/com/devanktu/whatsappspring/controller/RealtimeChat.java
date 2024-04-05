package com.devanktu.whatsappspring.controller;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class RealtimeChat {

//    private SimpMessagingTemplate simpMessagingTemplate;
//
//    @MessageMapping("/message")
//    @SendTo("/goup/public")
//    public Message reciveMessage(@Payload Message message){
//        simpMessagingTemplate.convertAndSend("/goup/" + message.getChat().getId.toStrin(), message);
//        return message;
//    }

}
