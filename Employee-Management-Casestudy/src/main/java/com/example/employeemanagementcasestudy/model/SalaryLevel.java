package com.example.employeemanagementcasestudy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SalaryLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salaryLevelId;
    @Column(nullable = false)
    private int coefficientsSalary;
    @Column(nullable = false)
    private int allowance;

    @OneToMany(mappedBy = "salaryLevel")
    private Set<Contract> contractSet;
}
