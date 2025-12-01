package com.ai_study_assist.ai_study_assistant.controller;


import com.ai_study_assist.ai_study_assistant.entity.Notes;
import com.ai_study_assist.ai_study_assistant.service.SummarizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

@RequestMapping("api/user/notes/aiService")
public class GenerativeController
{
  private final SummarizationService summarizationService;

    @PostMapping("/summary")
    public String getChatResponse(@RequestBody Notes note){
        if (note.getContent() == null) {
            return "Content cannot be empty.";
        }
        System.out.println("Received content for summarization...");
        String summary = summarizationService.generateSummary(note.getContent());

        return summary;
    }
}
