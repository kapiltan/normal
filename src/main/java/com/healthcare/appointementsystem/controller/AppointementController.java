package com.healthcare.appointementsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.appointementsystem.model.Appointement;
import com.healthcare.appointementsystem.service.AppointementService;

@RestController
@RequestMapping("/appointements")
public class AppointementController {
    private final AppointementService appointementService;

    public AppointementController(AppointementService appointementService) {
        this.appointementService = appointementService;
    }

    @PostMapping
    public String createAppointement(@RequestBody Appointement appointement) {
        // Logic to create an appointement
        return appointementService.scheduleAppointement(appointement);
    }

    @GetMapping("/{appointementId}/summary")
    public String getAppointementSummary(@PathVariable String appointementId) {
        // Logic to get appointment summary

        Appointement appointement = appointementService.getAppointementById(appointementId);

        System.out.println("Received request for appointment summary with ID: " + appointementId);
        if (appointement == null || appointementId == null || appointementId.isEmpty()) {
            return "Invalid appointment ID";
        }
        return appointementService.getAppointementSummary(appointementId);
    }

}
