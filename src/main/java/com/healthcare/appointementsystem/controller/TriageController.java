package com.healthcare.appointementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.appointementsystem.model.Patient;
import com.healthcare.appointementsystem.model.TriageResponse;
import com.healthcare.appointementsystem.service.TriageService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TriageController {
    private final TriageService triageService;

    @PostMapping("/triage")
    public ResponseEntity<TriageResponse> triagePatient(
            @Valid @RequestBody Patient request) {

        TriageResponse response = triageService.processTriage(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/report/{patientId}")
    public ResponseEntity<?> getReport(
            @PathVariable String patientId) {

        TriageResponse response = triageService.getReport(patientId);

        if (response == null) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Patient report not found");
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/escalate/{patientId}")
    public ResponseEntity<?> escalatePatient(
            @PathVariable String patientId) {

        TriageResponse response = triageService.escalate(patientId);

        if (response == null) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Patient report not found");
        }

        return ResponseEntity.ok(response);
    }
}
