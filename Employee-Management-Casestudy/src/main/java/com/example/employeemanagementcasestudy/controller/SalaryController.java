package com.example.employeemanagementcasestudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/salary")
public class SalaryController {
    @GetMapping
    public String showSalaryPage(){
        return "/salary/list";
    }
}
