package org.ellaa.ai.Service;

import org.ellaa.ai.Entity.MessageEntity;
import org.ellaa.ai.Repository.MessageRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {
    private final MessageRepo repository;

    public MessageService(MessageRepo repository) {
        this.repository = repository;
    }

    public void save(String userMessage,
                     String aiResponse) {

        MessageEntity message = new MessageEntity();

        message.setUserMessage(userMessage);
        message.setAiResponse(aiResponse);
        message.setCreatedAt(LocalDateTime.now());

        repository.save(message);

    }
}