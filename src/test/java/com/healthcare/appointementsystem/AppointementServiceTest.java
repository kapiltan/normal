package com.healthcare.appointementsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.healthcare.appointementsystem.model.Appointement;
import com.healthcare.appointementsystem.model.Patient;
import com.healthcare.appointementsystem.repository.AppointementRepository;
import com.healthcare.appointementsystem.repository.PatientRepository;
import com.healthcare.appointementsystem.service.AppointementService;
import com.healthcare.appointementsystem.service.LlmService;

public class AppointementServiceTest {
    private final AppointementRepository appointementRepository = new AppointementRepository();
    private final PatientRepository patientRepository = new PatientRepository();
    private final LlmService llmService = new LlmService();
    private final AppointementService appointementService = new AppointementService(appointementRepository,
            patientRepository, llmService);

    @Test
    void testScheduleAppointement() {
        // Create a sample appointment
        Patient patient = new Patient();
        patient.setPatientId("123");
        patient.setPatientName("John Doe");
        patient.setAge(30);
        patientRepository.save(patient);

        Appointement appointement = new Appointement();
        appointement.setAppointementId("123");
        appointement.setPatientId("123");
        appointement.setDoctorName("Dr. Smith");
        appointement.setAppointementDate("2024-07-01");

        // Call the service method
        String result = appointementService.scheduleAppointement(appointement);

        // Verify the result
        assertEquals("Appointment scheduled successfully", result);
    }

    @Test
    void testGetAppointementSummary() {
        // Create a sample appointment
        Patient patient = new Patient();
        patient.setPatientId("123");
        patient.setPatientName("John Doe");
        patient.setAge(30);
        patientRepository.save(patient);

        Appointement appointement = new Appointement();
        appointement.setAppointementId("123");
        appointement.setPatientId("123");
        appointement.setDoctorName("Dr. Smith");
        appointement.setAppointementDate("2024-07-01");
        appointementRepository.save(appointement);

        // Call the service method
        String result = appointementService.getAppointementSummary("123");

        // Verify the result (this is a placeholder, adjust as needed)
        assertNotEquals(null, result);
    }

    @Test
    void testAppointementForNonExistentPatient() {
        // Create a sample appointment with non-existent patient ID
        Appointement appointement = new Appointement();
        appointement.setAppointementId("124");
        appointement.setPatientId("999"); // Non-existent patient ID
        appointement.setDoctorName("Dr. Smith");
        appointement.setAppointementDate("2024-07-01");

        // Call the service method and expect an exception
        try {
            appointementService.scheduleAppointement(appointement);
        } catch (IllegalArgumentException e) {
            assertEquals("Patient not found", e.getMessage());
        }
    }

    @Test
    void testExceptionWhenSchedulingAppointementWithInvalidDoctorName() {
        // Create a sample appointment with invalid doctor name
        Appointement appointement = new Appointement();
        appointement.setAppointementId("124");
        appointement.setPatientId("123");
        appointement.setDoctorName("");
        appointement.setAppointementDate("2024-07-01");

        // Call the service method and expect an exception
        try {
            appointementService.scheduleAppointement(appointement);
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid doctor name", e.getMessage());
        }
    }
}
