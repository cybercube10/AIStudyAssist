package com.ai_study_assist.ai_study_assistant.entity;

import jakarta.persistence.*;

public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String summary;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="notes_id",referencedColumnName = "notes_id")
    private Notes notes;
}
