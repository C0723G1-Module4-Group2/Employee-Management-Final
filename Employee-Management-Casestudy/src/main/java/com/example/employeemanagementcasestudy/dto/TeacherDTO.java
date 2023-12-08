package com.example.employeemanagementcasestudy.dto;

import com.example.employeemanagementcasestudy.model.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
    private int teacherId;
    private String teacherCode;
    private String teacherName;
    private Date dateOfBirth;
    private String address;
    private String mail;
    private boolean gender;
    private String phoneNumber;
    private String identificationCard;
    private int basicSalary;
    private int warningCoefficient;
    private boolean status;
    private String imgOfTeacher;
    private AppUser appUser;
    private Contract contract;
    private String contractCode;
    private Date startDate;
    private Date endDate;
    private SalaryLevel salaryLevel;
    private List<AppRole> appRoles;
}
