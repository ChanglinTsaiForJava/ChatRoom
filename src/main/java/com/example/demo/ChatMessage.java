package com.example.demo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//lombok can be seen as a version of entity
@Data
//自動生成getter/setter
@AllArgsConstructor
//自動生成全包建構子
public class ChatMessage {
    private Long id;
    private String sender;
    private String content;
}
