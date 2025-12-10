package com.ai_study_assist.ai_study_assistant.controller;

import com.ai_study_assist.ai_study_assistant.entity.Notes;
import com.ai_study_assist.ai_study_assistant.service.AIService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class AIController {
    private final ChatClient chatClient;

    AIController(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }
    @PostMapping("/api/chat")
    Output chat(@RequestBody @Valid Input input) {
        String hardcodedPrompt = "Generate a summary of my notes about AI";

        String response = chatClient.prompt(hardcodedPrompt).call().content();
        return new Output(response);
    }

    record Input(String prompt) {}
    record Output(String content) {}

//@Autowired
//private AIService aiService;

//    @GetMapping("ask")
//    public String generateSummary(Notes notes) {
////        try {
////            return aiService.performTask("generate summary of my notes");
////        } catch (Exception ex) {
////            ex.printStackTrace();
////            return "Error occurred: " + ex.getMessage();
////        }
//
//
//    }


}
