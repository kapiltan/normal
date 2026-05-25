package com.healthcare.appointementsystem.model;

import org.springframework.stereotype.Service;

@Service
public class Patient {
    private String patientId;
    private String patientName;
    private int age;
    private String symptomps;

    public Patient() {

    }

    public Patient(String patientId, String patientName, int age, String symptomps) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.age = age;
        this.symptomps = symptomps;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSymptomps() {
        return symptomps;
    }

    public void setSymptomps(String symptomps) {
        this.symptomps = symptomps;
    }

}
