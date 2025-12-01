package com.ai_study_assist.ai_study_assistant.config;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.ai.huggingface.HuggingFaceChatModel;

@Configuration
public class AiConfig {

    @Value("${spring.ai.huggingface.api-key}")
    private String apiKey;

    @Value("${spring.ai.huggingface.chat.model}")
    private String modelName;

    @Value("${spring.ai.huggingface.chat.url:}")
    private String apiUrl;




}
