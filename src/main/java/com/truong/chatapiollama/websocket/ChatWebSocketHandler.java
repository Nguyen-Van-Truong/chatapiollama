package com.truong.chatapiollama.websocket;

import com.truong.chatapiollama.service.OllamaService;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final OllamaService ollamaService;

    public ChatWebSocketHandler(OllamaService ollamaService) {
        this.ollamaService = ollamaService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Connected: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String modelName = "llama3.2:1b";
        String input = message.getPayload();

        ollamaService.streamResponse(modelName, input, (String output) -> {
            try {
                session.sendMessage(new TextMessage(output));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
