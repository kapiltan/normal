package com.healthcare.appointementsystem.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.appointementsystem.model.Department;

import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentRepository {

    private final List<Department> departments = new ArrayList<>();

    @PostConstruct
    public void loadDepartments() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            InputStream inputStream = new ClassPathResource("departments.json").getInputStream();

            JsonNode root = mapper.readTree(inputStream);

            JsonNode departmentNodes = root.get("departments");

            for (JsonNode node : departmentNodes) {
                Department department = mapper.treeToValue(node, Department.class);

                departments.add(department);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to load departments", e);
        }
    }

    public List<Department> getAllDepartments() {
        return departments;
    }
}