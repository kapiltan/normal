package com.healthcare.appointementsystem.model;

import org.springframework.stereotype.Service;

@Service
public class Appointement {
    private String appointementId;
    private String patientId;
    private String doctorName;
    private String appointementDate;

    public Appointement() {

    }

    public Appointement(String appointementId, String patientId, String doctorName, String appointementDate) {
        this.appointementId = appointementId;
        this.patientId = patientId;
        this.doctorName = doctorName;
        this.appointementDate = appointementDate;
    }

    public String getAppointementId() {
        return appointementId;
    }

    public void setAppointementId(String appointementId) {
        this.appointementId = appointementId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getAppointementDate() {
        return appointementDate;
    }

    public void setAppointementDate(String appointementDate) {
        this.appointementDate = appointementDate;
    }
}
