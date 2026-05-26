package com.healthcare.appointementsystem.model;

import java.util.List;

import lombok.Data;

@Data
public class Department {

    private String departmentId;

    private String name;

    private String specialty;

    private Integer availableSlots;

    private List<String> acceptsTriageLevels;

    private String contactExt;

    private String hours;

    public Department() {
    }

    public Department(String departmentId, String name, String specialty, Integer availableSlots,
            List<String> acceptsTriageLevels, String contactExt, String hours) {
        this.departmentId = departmentId;
        this.name = name;
        this.specialty = specialty;
        this.availableSlots = availableSlots;
        this.acceptsTriageLevels = acceptsTriageLevels;
        this.contactExt = contactExt;
        this.hours = hours;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Integer getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(Integer availableSlots) {
        this.availableSlots = availableSlots;
    }

    public List<String> getAcceptsTriageLevels() {
        return acceptsTriageLevels;
    }

    public void setAcceptsTriageLevels(List<String> acceptsTriageLevels) {
        this.acceptsTriageLevels = acceptsTriageLevels;
    }

    public String getContactExt() {
        return contactExt;
    }

    public void setContactExt(String contactExt) {
        this.contactExt = contactExt;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}
