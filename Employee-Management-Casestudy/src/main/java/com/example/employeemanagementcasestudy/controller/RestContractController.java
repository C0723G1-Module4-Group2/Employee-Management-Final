package com.example.employeemanagementcasestudy.controller;

import com.example.employeemanagementcasestudy.model.Contract;
import com.example.employeemanagementcasestudy.service.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api-contract")
public class RestContractController {
    @Autowired
    private IContractService contractService;
    @GetMapping("")
    public ResponseEntity<Page<Contract>> findAll(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "") String search){
        Pageable pageable = PageRequest.of(page,10, Sort.by("contract_code").ascending());
        Page<Contract> contractPage = contractService.findAllContract(search,pageable);
        if (contractPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contractPage,HttpStatus.OK);
    }
}
