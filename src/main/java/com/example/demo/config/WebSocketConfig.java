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
    //這個方法用來定義 WebSocket 端點，並設置其可訪問的來源。
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // addEndPoint這是設定 WebSocket 的端點 URL，客戶端會連接到這個端點來建立 WebSocket 連接。
        // 在這個例子中，端點是 /chat，所以客戶端需要連接到 ws://localhost:8080/chat
        // 或 http://localhost:8080/chat（根據是否使用 withSockJS()）。
        registry.addEndpoint("/chat")
                //setAllowedOrigins("http://localhost:8080")：這行設定允許哪些來源（來源是指 Web 前端的域名或 IP）
                // 可以訪問這個 WebSocket 端點。在這個例子中，只有 http://localhost:8080
                // 這個來源可以連接 WebSocket。這有助於保護你的 WebSocket 服務器，防止來自不同來源的非法請求。
                .setAllowedOrigins("http://localhost:8080")
                //讓我們可以用http當開頭
                .withSockJS();
    }

    @Override
    //這個方法用來設定訊息代理，處理消息的傳遞和轉發。
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //set message broker(handle messages)
        //這行啟用了簡單的訊息代理（message broker）。在 WebSocket 中，
        // 消息是通過主題（topic）來分發的。這行代碼表示，任何以 /topic 開頭的消息都會由訊息代理處理。
        // 例如，如果你有一個客戶端訂閱了 /topic/chatRoom，它就會接收到從該主題發送過來的消息。
        // 下面這行會丟給controller
        registry.enableSimpleBroker("/topic"); // /topic/chatRoom
        //expect message with /app/sendmessage
        //這行設置了應用的前綴（destination prefix）。
        // 當客戶端發送消息時，消息會被發送到以 /app 開頭的目的地。
        // 這樣，當你想發送一條消息到應用時，應該使用 /app/sendmessage 之類的 URL。
        // 這個前綴是用來區分應用端處理的消息和代理端處理的消息。
        registry.setApplicationDestinationPrefixes("/app");

    }
}
