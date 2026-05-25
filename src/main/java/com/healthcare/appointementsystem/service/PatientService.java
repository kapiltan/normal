package com.healthcare.appointementsystem.service;

import org.springframework.stereotype.Service;

import com.healthcare.appointementsystem.model.Patient;
import com.healthcare.appointementsystem.repository.PatientRepository;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public String registerPatient(Patient patient) {
        // Logic to register a patient

        if (patient.getAge() <= 0) {
            throw new IllegalArgumentException("Age must be greater than 0");
        }

        if (patient.getPatientName() == null || patient.getPatientName().isEmpty()) {
            throw new IllegalArgumentException("Patient name cannot be null or empty");
        }
        patientRepository.save(patient);

        return "patient registered successfully";
    }

    public Patient getPatientDetails(Patient patient) {
        // Logic to retrieve patient details
        if (!patientRepository.existsById(patient.getPatientId())) {
            throw new IllegalArgumentException("Patient not found");
        }
        return patientRepository.findById(patient.getPatientId());
    }
}
