package com.example.employeemanagementcasestudy.dto;


import com.example.employeemanagementcasestudy.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class SalaryDto {
    private Teacher teacher;
    private int coefficientSalary;
    private int allowance;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getCoefficientSalary() {
        return coefficientSalary;
    }

    public void setCoefficientSalary(int coefficientSalary) {
        this.coefficientSalary = coefficientSalary;
    }

    public int getAllowance() {
        return allowance;
    }

    public void setAllowance(int allowance) {
        this.allowance = allowance;
    }
}
