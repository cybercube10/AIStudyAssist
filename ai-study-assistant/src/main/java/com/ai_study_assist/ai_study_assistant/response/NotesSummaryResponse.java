package com.ai_study_assist.ai_study_assistant.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class NotesSummaryResponse {
    private Long notesId;
    private  Long userId;
    private String noteTitle;
    private String noteContent;

    @Builder.Default
    private List<String> practiceQuestions = new ArrayList<>();

}
