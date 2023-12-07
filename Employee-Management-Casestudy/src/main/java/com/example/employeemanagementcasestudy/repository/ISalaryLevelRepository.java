package com.example.employeemanagementcasestudy.repository;

import com.example.employeemanagementcasestudy.model.SalaryLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISalaryLevelRepository extends JpaRepository<SalaryLevel,Integer> {
}
