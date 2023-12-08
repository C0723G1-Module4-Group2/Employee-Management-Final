package com.example.employeemanagementcasestudy.service.impl;


import com.example.employeemanagementcasestudy.model.TimeSheets;
import com.example.employeemanagementcasestudy.repository.ITimeSheetsRepository;
import com.example.employeemanagementcasestudy.service.ITimeSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TimeSheetsService implements ITimeSheetService {
    @Autowired
    private ITimeSheetsRepository timeSheetsRepository;
    @Override
    public TimeSheets getTimeSheet(int id) {
        return timeSheetsRepository.findById(id).get();
    }

    @Override
    public List<TimeSheets> getAllTimeSheet() {
        return timeSheetsRepository.findAll();
    }
}
