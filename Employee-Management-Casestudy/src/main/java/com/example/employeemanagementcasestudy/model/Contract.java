package com.example.employeemanagementcasestudy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contractId;
    @Column(nullable = false,unique = true)
    private String contractCode;
    @Column(nullable = false)
    private Date startDate;
    private Date endDate;

    @ManyToOne
    @JoinColumn(name="salary_level_id", nullable = false)
    private SalaryLevel salaryLevel;
    private boolean status;
}