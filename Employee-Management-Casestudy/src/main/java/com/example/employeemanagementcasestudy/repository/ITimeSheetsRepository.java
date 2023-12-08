package com.example.employeemanagementcasestudy.repository;


import com.example.employeemanagementcasestudy.model.TimeSheets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITimeSheetsRepository extends JpaRepository<TimeSheets,Integer> {
}
