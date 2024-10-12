package com.truong.chatapiollama.controller;

import com.truong.chatapiollama.service.OllamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatbotController {

    @Autowired
    private OllamaService ollamaService;

    @PostMapping("/send")
    public String sendMessage(@RequestParam String modelName, @RequestParam String message) {
        return ollamaService.runOllamaModel(modelName, message);
    }
}
