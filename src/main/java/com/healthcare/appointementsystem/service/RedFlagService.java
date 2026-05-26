package com.healthcare.appointementsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.healthcare.appointementsystem.model.Patient;
import com.healthcare.appointementsystem.model.TriageLevel;

@Service
public class RedFlagService {
    public RedFlagResult evaluate(Patient patient) {

        List<String> symptoms = patient.getSymptoms()
                .stream()
                .map(String::toLowerCase)
                .toList();

        List<String> redFlags = new ArrayList<>();

        TriageLevel triageLevel = null;

        // Cardiac Risk
        if (symptoms.contains("chest pain")
                && symptoms.contains("shortness of breath")) {

            redFlags.add("CARDIAC_EVENT_RISK");
            triageLevel = TriageLevel.EMERGENCY;
        }

        // Stroke Risk
        if (symptoms.contains("face drooping")
                || symptoms.contains("arm weakness")
                || symptoms.contains("speech difficulty")) {

            redFlags.add("STROKE_RISK");
            triageLevel = TriageLevel.EMERGENCY;
        }

        // Pediatric Fever
        if (patient.getAge() <= 12
                && symptoms.stream().anyMatch(s -> s.contains("fever"))) {

            redFlags.add("PEDIATRIC_HIGH_FEVER");
            triageLevel = TriageLevel.URGENT;
        }

        return new RedFlagResult(
                triageLevel,
                redFlags);
    }

    public record RedFlagResult(
            TriageLevel triageLevel,
            List<String> redFlags) {
    }
}
