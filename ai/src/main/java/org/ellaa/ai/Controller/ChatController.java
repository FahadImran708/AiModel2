package org.ellaa.ai.Controller;

import org.ellaa.ai.Service.AiService;
import org.ellaa.ai.Dto.ChatRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatController {

    private final org.ellaa.ai.Service.AiService aiService;

    public ChatController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/message")
    public String send(@RequestBody ChatRequest chat) {

        return aiService.sendData(chat);
    }
}