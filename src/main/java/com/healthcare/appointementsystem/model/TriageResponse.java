package com.healthcare.appointementsystem.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TriageResponse {
    private String patientId;

    private String triageLevel;

    private Double confidenceScore;

    private String reasoning;

    private List<String> redFlags;

    private String matchedDepartment;

    private String recommendedAction;

    private Integer estimatedWaitMinutes;

    private Boolean capacityFlag;

    public TriageResponse() {
    }

    public TriageResponse(String patientId, String triageLevel, Double confidenceScore, String reasoning,
            List<String> redFlags, String matchedDepartment, String recommendedAction, Integer estimatedWaitMinutes,
            Boolean capacityFlag) {
        this.patientId = patientId;
        this.triageLevel = triageLevel;
        this.confidenceScore = confidenceScore;
        this.reasoning = reasoning;
        this.redFlags = redFlags;
        this.matchedDepartment = matchedDepartment;
        this.recommendedAction = recommendedAction;
        this.estimatedWaitMinutes = estimatedWaitMinutes;
        this.capacityFlag = capacityFlag;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getTriageLevel() {
        return triageLevel;
    }

    public void setTriageLevel(String triageLevel) {
        this.triageLevel = triageLevel;
    }

    public Double getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(Double confidenceScore) {
        this.confidenceScore = confidenceScore;
    }

    public String getReasoning() {
        return reasoning;
    }

    public void setReasoning(String reasoning) {
        this.reasoning = reasoning;
    }

    public List<String> getRedFlags() {
        return redFlags;
    }

    public void setRedFlags(List<String> redFlags) {
        this.redFlags = redFlags;
    }

    public String getMatchedDepartment() {
        return matchedDepartment;
    }

    public void setMatchedDepartment(String matchedDepartment) {
        this.matchedDepartment = matchedDepartment;
    }

    public String getRecommendedAction() {
        return recommendedAction;
    }

    public void setRecommendedAction(String recommendedAction) {
        this.recommendedAction = recommendedAction;
    }

    public Integer getEstimatedWaitMinutes() {
        return estimatedWaitMinutes;
    }

    public void setEstimatedWaitMinutes(Integer estimatedWaitMinutes) {
        this.estimatedWaitMinutes = estimatedWaitMinutes;
    }

    public Boolean getCapacityFlag() {
        return capacityFlag;
    }

    public void setCapacityFlag(Boolean capacityFlag) {
        this.capacityFlag = capacityFlag;
    }
}
