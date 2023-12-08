package com.example.employeemanagementcasestudy.dto;

import com.example.employeemanagementcasestudy.model.AppUser;
import com.example.employeemanagementcasestudy.model.Contract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


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

}
