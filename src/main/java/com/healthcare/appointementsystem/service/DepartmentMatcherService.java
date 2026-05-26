package com.healthcare.appointementsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.healthcare.appointementsystem.model.Department;
import com.healthcare.appointementsystem.model.TriageLevel;
import com.healthcare.appointementsystem.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentMatcherService {
    private final DepartmentRepository repository;

    public MatchResult matchDepartment(TriageLevel triageLevel) {

        List<Department> departments = repository.getAllDepartments();

        // First available department
        for (Department department : departments) {

            boolean accepts = department.getAcceptsTriageLevels()
                    .contains(triageLevel.name());

            if (accepts && department.getAvailableSlots() > 0) {

                return new MatchResult(
                        department,
                        false);
            }
        }

        // Capacity fallback
        for (Department department : departments) {

            boolean accepts = department.getAcceptsTriageLevels()
                    .contains(triageLevel.name());

            if (accepts) {

                return new MatchResult(
                        department,
                        true);
            }
        }

        return null;
    }

    public record MatchResult(
            Department department,
            Boolean capacityFlag) {
    }
}
