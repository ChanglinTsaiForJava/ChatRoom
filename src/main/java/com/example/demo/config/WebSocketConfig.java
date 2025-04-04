package com.example.demo.config;



import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
//message broker is our messenger
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //define the end point
        registry.addEndpoint("/chat")
                .setAllowedOrigins("http://localhost:8080")
                //add compatibility for clients don't support or not able to utilize websocket
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //set message broker(handle messages)
        registry.enableSimpleBroker("/topic"); // /topic/chatRoom
        //expect message with /app/sendmessage
        registry.setApplicationDestinationPrefixes("/app");

    }
}
