package com.healthcare.appointementsystem.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class AuditService {

    public void logEvent(
            String patientId,
            String action) {

        log.info(
                "AUDIT :: time={} patientId={} action={}",
                LocalDateTime.now(),
                patientId,
                action);
    }
}
