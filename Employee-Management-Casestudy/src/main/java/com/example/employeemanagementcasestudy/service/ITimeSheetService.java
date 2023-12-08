package com.example.employeemanagementcasestudy.service;


import com.example.employeemanagementcasestudy.model.TimeSheets;

import java.util.List;

public interface ITimeSheetService {
    TimeSheets getTimeSheet(int id);
    List<TimeSheets> getAllTimeSheet();

}
