package com.healthcare.appointementsystem.service;

import com.healthcare.appointementsystem.model.Patient;
import com.healthcare.appointementsystem.model.TriageLevel;
import org.springframework.stereotype.Service;

@Service
public class LLMService {

    public LLMResult assessPatient(Patient patient) {

        // Mock AI logic

        if (patient.getSymptoms()
                .stream()
                .anyMatch(s -> s.toLowerCase().contains("fever"))) {

            return new LLMResult(
                    TriageLevel.URGENT,
                    0.85,
                    "AI detected possible infection risk");
        }

        return new LLMResult(
                TriageLevel.STANDARD,
                0.75,
                "AI standard assessment");
    }

    public record LLMResult(
            TriageLevel triageLevel,
            Double confidenceScore,
            String reasoning) {
    }
}