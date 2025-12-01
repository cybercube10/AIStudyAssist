package com.ai_study_assist.ai_study_assistant.service;

import com.ai_study_assist.ai_study_assistant.config.HuggingFaceConfigProperties;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SummarizationService {

    private final ChatClient chatClient;

    public SummarizationService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String generateSummary(String content) {
        return chatClient.prompt()
                .user("You're a teaching assistant. Summarize the following content concisely:\n\n" + content)
                .call()
                .content();
    }
}
