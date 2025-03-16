package com.automon.controller;

import com.automon.service.OpenAIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAIController {

    private final OpenAIService openAIService;

    public OpenAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @GetMapping("/ai/generate")
    public String generateText(@RequestParam String prompt) {
        return openAIService.generateText(prompt);
    }
}

