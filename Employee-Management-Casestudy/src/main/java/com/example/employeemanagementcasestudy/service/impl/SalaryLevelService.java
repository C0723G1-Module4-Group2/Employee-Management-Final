package com.example.employeemanagementcasestudy.service.impl;

import com.example.employeemanagementcasestudy.model.SalaryLevel;
import com.example.employeemanagementcasestudy.repository.ISalaryLevelRepository;
import com.example.employeemanagementcasestudy.service.ISalaryLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryLevelService implements ISalaryLevelService {
    @Autowired
    private ISalaryLevelRepository salaryLevelRepository;
    @Override
    public List<SalaryLevel> findAllSalaryLevel() {
        return salaryLevelRepository.findAll();
    }
}
