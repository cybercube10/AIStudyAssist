package com.ai_study_assist.ai_study_assistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing

public class AiStudyAssistantApplication {

	public static void main(String[] args) {


        SpringApplication.run(AiStudyAssistantApplication.class, args);
	}


}
