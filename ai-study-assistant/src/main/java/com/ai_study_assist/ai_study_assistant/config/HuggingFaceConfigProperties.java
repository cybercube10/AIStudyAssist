package com.ai_study_assist.ai_study_assistant.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "huggingface")
public class HuggingFaceConfigProperties {

    private String apiKey;
    private ModelConfig model;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public ModelConfig getModel() {
        return model;
    }

    public void setModel(ModelConfig model) {
        this.model = model;
    }

    public static class ModelConfig {
        private String textGeneration;
        private String imageGeneration;

        public String getTextGeneration() {
            return textGeneration;
        }

        public void setTextGeneration(String textGeneration) {
            this.textGeneration = textGeneration;
        }

        public String getImageGeneration() {
            return imageGeneration;
        }

        public void setImageGeneration(String imageGeneration) {
            this.imageGeneration = imageGeneration;
        }
    }
}
