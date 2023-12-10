package com.example.employeemanagementcasestudy.controller;


import com.example.employeemanagementcasestudy.dto.ContractDto;
import com.example.employeemanagementcasestudy.dto.ContractDto1;
import com.example.employeemanagementcasestudy.model.Contract;
import com.example.employeemanagementcasestudy.model.SalaryLevel;
import com.example.employeemanagementcasestudy.service.IContractService;
import com.example.employeemanagementcasestudy.service.ISalaryLevelService;
import com.example.employeemanagementcasestudy.service.IUserRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/contracts")
public class ContractController {
    @Autowired
    private IContractService contractService;
    @Autowired
    private ISalaryLevelService salaryLevelService;
    @Autowired
    private IUserRoleService userRoleService;

    @GetMapping
    public String showList(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "") String searchCode,
                           Model model) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("contract_code").ascending());
        Page<ContractDto1> contractPage = contractService.getContract(searchCode, pageable);
        model.addAttribute("contractPage", contractPage);
        model.addAttribute("searchCode", searchCode);
        return "/contract/list";
    }


    @GetMapping("/create")
    public String showFormCreate(Model model) {
        List<SalaryLevel> salaryLevelList = salaryLevelService.findAllSalaryLevel();
        model.addAttribute("contractDto", new ContractDto());
        model.addAttribute("salaryLevelList", salaryLevelList);
        return "/contract/create";
    }
    @PostMapping("/save")
    public String createNewContract(@Valid @ModelAttribute ContractDto contractDto,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes,
                                    Model model
                                    ){
        if (bindingResult.hasErrors()){
            List<SalaryLevel> salaryLevelList = salaryLevelService.findAllSalaryLevel();
            model.addAttribute("salaryLevelList", salaryLevelList);
            return "/contract/create";
        }
        Contract contract = new Contract();
        long currentTimeMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimeMillis);
        BeanUtils.copyProperties(contractDto,contract);
        if (contractService.findByCode(contractDto.getContractCode()) == null){
            contract.setStartDate(currentDate);
            contract.setStatus(true);
            contractService.createContract(contract);
            redirectAttributes.addFlashAttribute("message", "Create new contract successful");
            return "redirect:/contracts";
        }else {
            redirectAttributes.addFlashAttribute("message", "Contract is exists");
            return "redirect:/contracts/create";
        }
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable int id, Model model, RedirectAttributes redirectAttributes, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        if (!userRoleService.isUserAdmin(principal.getName())) {
            return "redirect:/403";
        }
        List<SalaryLevel> salaryLevelList = salaryLevelService.findAllSalaryLevel();
        Contract editContract = contractService.findById(id);
        if (editContract == null){
            redirectAttributes.addFlashAttribute("message","Not found");
            return "redirect:/contracts";
        }else {
            model.addAttribute("editContract",editContract);
            model.addAttribute("salaryLevelList",salaryLevelList);
            return "/contract/edit";
        }
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute ContractDto contractDto,BindingResult bindingResult, RedirectAttributes redirectAttributes,  Model model) {
        if (bindingResult.hasErrors()){
            return "/contract/edit";
        }
        Contract editContract = new Contract();
        BeanUtils.copyProperties(contractDto,editContract);
        contractService.updateContract(editContract);
        redirectAttributes.addFlashAttribute("message", "Update Success");
        return "redirect:/contracts";
    }
    @Transactional
    @GetMapping("/delete")
    public String delete(@RequestParam int id, RedirectAttributes attributes,Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        if (!userRoleService.isUserAdmin(principal.getName())) {
            return "redirect:/403";
        }
        contractService.deleteContract(id);
        attributes.addFlashAttribute("message", "Xoá thành công");
        return "redirect:/contracts";
    }
}
