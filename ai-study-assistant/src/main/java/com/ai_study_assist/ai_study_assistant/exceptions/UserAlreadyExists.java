package com.ai_study_assist.ai_study_assistant.exceptions;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists(String message) {
        super(message);
    }
}
