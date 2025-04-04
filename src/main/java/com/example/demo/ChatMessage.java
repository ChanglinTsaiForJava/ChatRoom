package com.example.demo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
public class ChatMessage {
    private Long id;
    private String sender;
    private String content;
}
