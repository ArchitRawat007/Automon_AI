package com.automon.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
public class OpenAIService {

    @Value("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")
    private String apiKey;

    private final String OPENAI_API_URL = "https://api.openai.com/v1/completions";

    private final RestTemplate restTemplate;

    public OpenAIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateText(String prompt) {
        // Construct headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        // Construct the body (a JSON body)
        String body = String.format("{\"model\":\"text-davinci-003\",\"prompt\":\"%s\",\"max_tokens\":100}", prompt);

        // Prepare the HTTP entity
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        // Send the request to OpenAI's API
        ResponseEntity<String> response = restTemplate.exchange(OPENAI_API_URL, HttpMethod.POST, entity, String.class);

        // Extract the text from the response
        return response.getBody();
    }
}
