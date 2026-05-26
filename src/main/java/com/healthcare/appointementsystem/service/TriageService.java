package com.healthcare.appointementsystem.service;

import java.util.UUID;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.healthcare.appointementsystem.model.Patient;
import com.healthcare.appointementsystem.model.TriageLevel;
import com.healthcare.appointementsystem.model.TriageResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TriageService {
    private final AuditService auditService;

    private final RedFlagService redFlagService;

    private final LLMService llmService;

    private final DepartmentMatcherService matcherService;

    private final Map<String, TriageResponse> reports = new ConcurrentHashMap<>();

    public TriageResponse processTriage(
            Patient request) {

        String patientId = UUID.randomUUID().toString();

        // Step 1: Red Flag Detection
        RedFlagService.RedFlagResult redFlagResult = redFlagService.evaluate(request);

        TriageLevel finalLevel;
        Double confidence;
        String reasoning;

        // Step 2: Override OR LLM
        if (redFlagResult.triageLevel() != null) {

            finalLevel = redFlagResult.triageLevel();
            confidence = 0.99;
            reasoning = "Critical red-flag override triggered";

        } else {

            LLMService.LLMResult llmResult = llmService.assessPatient(request);

            finalLevel = llmResult.triageLevel();
            confidence = llmResult.confidenceScore();
            reasoning = llmResult.reasoning();
        }

        // Step 3: Department Matching
        DepartmentMatcherService.MatchResult matchResult = matcherService.matchDepartment(finalLevel);

        // Step 4: Build Response
        TriageResponse response = TriageResponse.builder()
                .patientId(patientId)
                .triageLevel(finalLevel.name())
                .confidenceScore(confidence)
                .reasoning(reasoning)
                .redFlags(redFlagResult.redFlags())
                .matchedDepartment(
                        matchResult.department().getName())
                .recommendedAction(
                        buildAction(finalLevel))
                .estimatedWaitMinutes(
                        estimateWait(finalLevel))
                .capacityFlag(
                        matchResult.capacityFlag())
                .build();

        reports.put(patientId, response);

        auditService.logEvent(
                patientId,
                "TRIAGE_COMPLETED");

        return response;
    }

    public TriageResponse getReport(String patientId) {
        return reports.get(patientId);
    }

    public TriageResponse escalate(String patientId) {

        TriageResponse existing = reports.get(patientId);

        if (existing == null) {
            return null;
        }

        existing.setTriageLevel(
                TriageLevel.EMERGENCY.name());

        existing.setRecommendedAction(
                "Immediate emergency escalation");

        auditService.logEvent(
                patientId,
                "PATIENT_ESCALATED");

        return existing;
    }

    private String buildAction(TriageLevel level) {

        return switch (level) {

            case EMERGENCY ->
                "Immediate emergency intervention";

            case URGENT ->
                "Doctor consultation within 30 minutes";

            case STANDARD ->
                "Standard consultation recommended";

            case SELF_CARE ->
                "Home care and monitoring advised";
        };
    }

    private Integer estimateWait(TriageLevel level) {

        return switch (level) {

            case EMERGENCY -> 0;
            case URGENT -> 15;
            case STANDARD -> 45;
            case SELF_CARE -> 120;
        };
    }
}
