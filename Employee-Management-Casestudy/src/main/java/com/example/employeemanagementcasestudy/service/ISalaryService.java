package com.example.employeemanagementcasestudy.service;



import com.example.employeemanagementcasestudy.dto.SalaryDto;

import java.util.List;

public interface ISalaryService {

    List<SalaryDto> getAll();

//    SalaryDto displaySalaryById(int idTeacher);

    List<SalaryDto> getSalaryById(int teacherId);

}
