package com.example.employeemanagementcasestudy.service.impl;

import com.example.employeemanagementcasestudy.dto.ContractDto1;
import com.example.employeemanagementcasestudy.model.Contract;
import com.example.employeemanagementcasestudy.repository.IContractRepository;
import com.example.employeemanagementcasestudy.service.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService implements IContractService {
    @Autowired
    private IContractRepository contractRepository;
    @Override
    public List<Contract> getAllContract() {
        return contractRepository.findAll();
    }

    @Override
    public void createContract(Contract contract) {
        contractRepository.save(contract);
    }

    @Override
    public void deleteContract(int id) {
        contractRepository.deleteById(id);
    }

    @Override
    public void updateContract(Contract contract) {
        contractRepository.save(contract);
    }

    @Override
    public Contract findById(int id) {
        return contractRepository.findById(id).orElse(null);
    }

    @Override
    public Contract findByCode(String code) {
        return contractRepository.findByCode(code);
    }

    @Override
    public Page<Contract> findAllContract(String code, Pageable pageable) {
        return contractRepository.search("%" + code + "%",pageable);
    }

    @Override
    public Page<ContractDto1> getContract(String code, Pageable pageable) {
        return contractRepository.getContract("%" + code + "%",pageable);
    }

}
