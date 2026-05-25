package com.healthcare.appointementsystem.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.healthcare.appointementsystem.model.Patient;

@Repository
@Service
public class PatientRepository {
    private final Map<String, Patient> patients = new HashMap<>();

    public PatientRepository() {

    }

    public void save(Patient patient) {
        patients.put(patient.getPatientId(), patient);
    }

    public Patient findById(String patientId) {
        return patients.get(patientId);
    }

    public boolean existsById(String patientId) {
        return patients.containsKey(patientId);
    }
}
