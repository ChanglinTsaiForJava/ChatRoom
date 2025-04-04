package com.example.demo.controller;


import com.example.demo.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {
    @MessageMapping("/sendMessage")
    //map the message to destination
    @SendTo("/topic/messages")
    //whoever wish to get the message should have this endpoint
    public ChatMessage sendMessage(ChatMessage message){
        return message;
    }
    //連線到聊天室第一個觸發這個
    @GetMapping("chat")
    public String chat(){
        //找 chat.html
        return "chat";
    }
}
