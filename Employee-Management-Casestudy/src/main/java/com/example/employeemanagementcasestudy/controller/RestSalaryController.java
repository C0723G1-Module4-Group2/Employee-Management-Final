package com.example.employeemanagementcasestudy.controller;


import com.example.employeemanagementcasestudy.dto.SalaryDto;
import com.example.employeemanagementcasestudy.service.ISalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api-salary")
public class RestSalaryController {
    @Autowired
    private ISalaryService salaryService;

    @GetMapping
    public ResponseEntity<List<SalaryDto>> getAllSalary(){
        List<SalaryDto> salaryDtoList = salaryService.getAll();
        if(salaryDtoList.isEmpty()){
            return new ResponseEntity<>(salaryDtoList,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(salaryDtoList, HttpStatus.OK);
    }

//    @GetMapping("/change-teacher")
//    public ResponseEntity<SalaryDto> getBasicSalary(Model model, @RequestParam int idTeacher){
//        SalaryDto salaryDto = salaryService.displaySalaryById(idTeacher);
//        if(salaryDto == null){
//            return new ResponseEntity<>(salaryDto,HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(salaryDto, HttpStatus.OK);
//    }

    @GetMapping("change-teacher")
    public ResponseEntity<List<SalaryDto>> getSalaryById(@RequestParam int teacherId){
        List<SalaryDto> salaryDtoList = salaryService.getSalaryById(teacherId);
        if(salaryDtoList.isEmpty()){
            return new ResponseEntity<>(salaryDtoList,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(salaryDtoList, HttpStatus.OK);
    }

}
