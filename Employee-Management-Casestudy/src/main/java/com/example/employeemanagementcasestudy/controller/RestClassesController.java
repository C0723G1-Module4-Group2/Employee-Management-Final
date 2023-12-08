package com.example.employeemanagementcasestudy.controller;

import com.example.employeemanagementcasestudy.model.Classes;
import com.example.employeemanagementcasestudy.service.IClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api-classes")
public class RestClassesController {
@Autowired
    private IClassesService classesService;
@GetMapping("")
    public ResponseEntity<List<Classes>>  getAllClass(){
    List<Classes> classes = classesService.findAll();
    if (classes.isEmpty()) {
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }
    return new ResponseEntity<>(classes, HttpStatus.OK);
}
}
