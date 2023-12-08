package com.example.employeemanagementcasestudy.service;

import com.example.employeemanagementcasestudy.dto.ContractDto1;
import com.example.employeemanagementcasestudy.model.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IContractService {
    List<Contract> getAllContract();
    void createContract(Contract contract);
    void deleteContract(int id);
    void updateContract(Contract contract);

    Contract findById(int id);
    Contract findByCode(String code);
    Page<Contract> findAllContract(String code, Pageable pageable);

    Page<ContractDto1> getContract(String code, Pageable pageable);
}
