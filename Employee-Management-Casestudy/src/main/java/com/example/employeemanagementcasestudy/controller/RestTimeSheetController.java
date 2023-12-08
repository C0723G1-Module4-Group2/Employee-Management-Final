package com.example.employeemanagementcasestudy.controller;
import com.example.employeemanagementcasestudy.model.TimeSheets;
import com.example.employeemanagementcasestudy.service.ITimeSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api-sheets")
public class RestTimeSheetController {
    @Autowired
    private ITimeSheetService timeSheetService;
    @GetMapping("")
    public ResponseEntity<List<TimeSheets>> getList() {
        List<TimeSheets> timeSheets = timeSheetService.getAllTimeSheet();
        if (timeSheets.isEmpty()) {
            return new ResponseEntity<>(timeSheets,HttpStatus.OK);
        }
        return new ResponseEntity<>(timeSheets, HttpStatus.OK);
    }
}
