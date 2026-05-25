package com.healthcare.appointementsystem.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.appointementsystem.model.Patient;
import com.healthcare.appointementsystem.service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public String createPatient(@RequestBody Patient patient) {
        // Logic to create a patient

        return patientService.registerPatient(patient);
    }
}
