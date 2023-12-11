package com.example.employeemanagementcasestudy.service.impl;


import com.example.employeemanagementcasestudy.dto.SalaryDto;
import com.example.employeemanagementcasestudy.repository.ISalaryRepository;
import com.example.employeemanagementcasestudy.service.ISalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryService implements ISalaryService {
    @Autowired
    private ISalaryRepository salaryRepository;

    @Override
    public List<SalaryDto> getAll() {
        return salaryRepository.getAll();
    }

//    @Override
//    public SalaryDto displaySalaryById(int idTeacher) {
//        return salaryRepository.displaySalaryById(idTeacher);
//    }


    @Override
    public List<SalaryDto> getSalaryById(int teacherId) {
        return salaryRepository.getSalaryById(teacherId);
    }

}
