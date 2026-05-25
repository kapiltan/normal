package com.healthcare.appointementsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.healthcare.appointementsystem.model.Patient;
import com.healthcare.appointementsystem.repository.PatientRepository;
import com.healthcare.appointementsystem.service.PatientService;

public class PatientServiceTest {
    private final PatientRepository patientRepository = new PatientRepository();
    private final PatientService patientService = new PatientService(patientRepository);

    @Test
    void testRegisterPatient() {
        // Create a sample patient
        Patient patient = new Patient();
        patient.setPatientId("123");
        patient.setPatientName("John Doe");
        patient.setAge(30);

        // Call the service method
        String result = patientService.registerPatient(patient);

        // Verify the result
        assertEquals("patient registered successfully", result);
    }

    @Test
    void shouldThrowExceptionWhenRegisteringPatientWithInvalidAge() {
        // Create a sample patient with invalid age
        Patient patient = new Patient();
        patient.setPatientId("124");
        patient.setPatientName("Jane Doe");
        patient.setAge(-5);

        // Call the service method and expect an exception
        try {
            patientService.registerPatient(patient);
        } catch (IllegalArgumentException e) {
            assertEquals("Age must be greater than 0", e.getMessage());
        }
    }
}
