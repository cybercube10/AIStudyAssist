package com.ai_study_assist.ai_study_assistant.service;

import com.ai_study_assist.ai_study_assistant.entity.Notes;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;

import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AIService {
    @Autowired
    private OllamaChatModel ollamaChatModel;

    public String performTask(String prompt) {
        String chatResponse = ollamaChatModel.call(
                String.valueOf(Prompt.builder()
                        .content(prompt)
                        .chatOptions(
                                OllamaChatOptions.builder()
                                        .model("llama3.2:1b")
                                        .temperature(0.2)
                                        .build()
                        )
                        .build())
        );

        return chatResponse.toString();  // return the output string
    }

}
