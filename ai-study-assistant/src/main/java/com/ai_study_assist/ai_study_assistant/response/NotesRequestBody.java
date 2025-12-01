package com.ai_study_assist.ai_study_assistant.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotesRequestBody {
    private final Long id = 1L;
    private String title;
    private String content;
}
