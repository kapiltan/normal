package com.healthcare.appointementsystem.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import com.healthcare.appointementsystem.model.Appointement;
import com.healthcare.appointementsystem.model.Patient;

@Service
public class LlmService {
    // public String generateSummary(patient patient, appointement appointement) {
    // return "Patient: " + patient.getPatientName() + " (Age: " + patient.getAge()
    // + ") reports: "
    // + patient.getSymptomps() + ". Recommended consultation with " +
    // appointement.getDoctorName() + ".";
    // }

    @Value("${openai.api.key}")
    private String apiKey;

    private final WebClient webClient = WebClient.create("https://api.openai.com/v1/chat/completions");

    public String generateSummary(Patient patient, Appointement appointement) {
        String prompt = "Generate a medical summary for " + patient.getPatientName();

        return webClient.post()
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(Map.of("model", "gpt-3.5-turbo", "messages",
                        List.of(Map.of("role", "user", "content", prompt))))
                .retrieve().bodyToMono(String.class).block();
    }
}
