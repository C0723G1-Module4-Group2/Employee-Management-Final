package com.example.employeemanagementcasestudy.dto;

import java.sql.Date;
import java.time.LocalDate;

public interface ContractDto1 {
     int getContractId();
     String getContractCode();
     Date getStartDate();
     Date getEndDate();
     int getCoefficientsSalary();
}
