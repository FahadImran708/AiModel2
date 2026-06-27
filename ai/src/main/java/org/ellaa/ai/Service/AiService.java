package org.ellaa.ai.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ellaa.ai.Dto.ChatRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AiService {

    @Value("${my.api.key}")
    private String api;

    private final RestClient restClient;

    public AiService() {

        this.restClient = RestClient.builder()
                .baseUrl("https://openrouter.ai/api/v1")
                .build();
    }

    public String sendData(ChatRequest chat) {

        String requestBody =
                """
                {
                  "model": "deepseek/deepseek-chat-v3-0324",
                  "messages": [
                    {
                      "role": "user",
                      "content": "%s"
                    }
                  ]
                }
                """.formatted(chat.getMessage());

        try {

            String response = restClient.post()
                    .uri("/chat/completions")
                    .header("Authorization", "Bearer " + api)
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                    .retrieve()
                    .body(String.class);

            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(response);

            return root.get("choices")
                    .get(0)
                    .get("message")
                    .get("content")
                    .asText();

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}