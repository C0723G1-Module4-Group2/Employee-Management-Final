package com.example.employeemanagementcasestudy.dto;

import com.example.employeemanagementcasestudy.model.SalaryLevel;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Date;


public class ContractDto{
    private int contractId;
    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "^CT-\\d{3}$", message = "Vui lòng nhập đúng định dạng CT-xxx(x là số bất kì)")
    private String contractCode;
    private Date startDate;

    @NotNull(message = "Không được để trống")
    private Date endDate;
    private SalaryLevel salaryLevel;


    public ContractDto() {
    }

    public ContractDto(int contractId, String contractCode, Date startDate, Date endDate, SalaryLevel salaryLevel) {
        this.contractId = contractId;
        this.contractCode = contractCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.salaryLevel = salaryLevel;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public SalaryLevel getSalaryLevel() {
        return salaryLevel;
    }

    public void setSalaryLevel(SalaryLevel salaryLevel) {
        this.salaryLevel = salaryLevel;
    }
}
