package com.ai_study_assist.ai_study_assistant.controller;

import com.ai_study_assist.ai_study_assistant.entity.Notes;
import com.ai_study_assist.ai_study_assistant.service.AIService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class AIController {
    private final ChatClient chatClient;

    AIController(ChatClient.Builder builder, ChatMemory  chatMemory) {
        this.chatClient = builder
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory).build(),
                        new SimpleLoggerAdvisor())
                .build();
    }
    @PostMapping("/api/chat")
    Output chat(@RequestBody @Valid Input input, @CookieValue(name="X-CONVO-ID",required=false) String convoId) {
        String conversationId = convoId == null? UUID.randomUUID().toString() : convoId;
        String promptAdvice = """
You are a helpful assistant. Your ONLY job:
- Summarize the user's note clearly and intuitively
- Add 1–2 useful learning resources
- Stay ONLY within the note’s context
- Do NOT talk about anything outside the note
""";

        String response = chatClient.prompt(promptAdvice+ "\n\nUser Note:\n" + input.prompt())
                .advisors(a->a.param(ChatMemory.CONVERSATION_ID,conversationId))
                .call()
                .content();
        ResponseCookie cookie = ResponseCookie.from("X-CONV-ID",conversationId)
                .path("/")
                .maxAge(3600)
                .build();
        Output output = new  Output(response);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,cookie.toString()).body(output).getBody();
    }

    record Input(String prompt) {}
    record Output(String content) {}




}
