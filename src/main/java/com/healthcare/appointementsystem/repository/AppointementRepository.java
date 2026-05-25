package com.healthcare.appointementsystem.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.healthcare.appointementsystem.model.Appointement;

@Service
public class AppointementRepository {
    private final Map<String, Appointement> appointements = new HashMap<>();

    public AppointementRepository() {

    }

    public void save(Appointement appointement) {
        appointements.put(appointement.getAppointementId(), appointement);
    }

    public Appointement findById(String appointementId) {
        return appointements.get(appointementId);
    }

    public boolean existsById(String appointementId) {
        return appointements.containsKey(appointementId);
    }
}
