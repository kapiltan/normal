package com.healthcare.appointementsystem.service;

import org.springframework.stereotype.Service;

import com.healthcare.appointementsystem.model.Appointement;
import com.healthcare.appointementsystem.repository.AppointementRepository;
import com.healthcare.appointementsystem.repository.PatientRepository;
import com.healthcare.appointementsystem.exception.ResourceNotFoundException;

@Service
public class AppointementService {
    private final AppointementRepository appointementRepository;
    private final PatientRepository patientRepository;
    private final LlmService llmService;

    public AppointementService(AppointementRepository appointementRepository, PatientRepository patientRepository,
            LlmService llmService) {
        this.appointementRepository = appointementRepository;
        this.patientRepository = patientRepository;
        this.llmService = llmService;
    }

    public String scheduleAppointement(Appointement appointement) {
        // Validate patient ID
        if (appointement.getDoctorName() == null || appointement.getDoctorName().isEmpty()) {
            throw new IllegalArgumentException("Invalid doctor name");
        }

        if (!patientRepository.existsById(appointement.getPatientId())) {
            throw new IllegalArgumentException("Patient not found");
        }

        // Save appointment to database (simulated)
        appointementRepository.save(appointement);

        return "Appointment scheduled successfully";
    }

    public String getAppointementSummary(String appointementId) {
        // Validate appointment ID exists before loading the appointment
        if (!appointementRepository.existsById(appointementId)) {
            throw new ResourceNotFoundException("Appointment not found");
        }

        Appointement appointement = appointementRepository.findById(appointementId);
        String appointmentDetails = llmService.generateSummary(patientRepository.findById(appointement.getPatientId()),
                appointement);

        return appointmentDetails;
    }

    public Appointement getAppointementById(String appointementId) {
        if (!appointementRepository.existsById(appointementId)) {
            throw new ResourceNotFoundException("Appointment not found");
        }
        return appointementRepository.findById(appointementId);
    }
}
