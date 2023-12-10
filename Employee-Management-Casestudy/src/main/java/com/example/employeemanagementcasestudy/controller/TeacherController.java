package com.example.employeemanagementcasestudy.controller;


import com.example.employeemanagementcasestudy.dto.TeacherDTO;
import com.example.employeemanagementcasestudy.model.*;
import com.example.employeemanagementcasestudy.service.*;
import com.example.employeemanagementcasestudy.util.EncrytedPasswordUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller()
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private ITeacherService iTeacherService;
    @Autowired
    private IContractService iContractService;
    @Autowired
    private IAppUserService iAppUserService;
    @Autowired
    private ISalaryLevelService salaryLevelService;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IAppRoleService appRoleService;

    @GetMapping("")
    public String showManagementTeacher(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "") String searchName,
                                        Model model) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("teacher_name").ascending());
        Page<Teacher> teacherPage = iTeacherService.displayAllTeacher(searchName, pageable);
        model.addAttribute("teacherPage", teacherPage);
        model.addAttribute("searchName", searchName);
        model.addAttribute("teacher", new Teacher());

        return "/teacher/managementTeacher";
    }

    @GetMapping("/add")
    public String showFormTeacher(Model model) {
        model.addAttribute("teacherDTO", new TeacherDTO());
        List<SalaryLevel> salaryLevelList = salaryLevelService.findAllSalaryLevel();
        model.addAttribute("salaryLevelList", salaryLevelList);
        List<AppRole> appRoleList = appRoleService.findAllAppRole();
        model.addAttribute("appRoleList", appRoleList);
        return "/teacher/add";
    }

    //    @PostMapping("/add")
//    public String addNewTeacher(TeacherDTO teacherDTO){
//        int salary = teacherDTO.getBasicSalary();
//        teacherDTO.setBasicSalary(salary);
//       Teacher teacher = new Teacher();
//        BeanUtils.copyProperties(teacherDTO,teacher);
//        iTeacherService.addNewTeacher(teacher);
//        return "redirect:/teacher";
//    }
    @PostMapping("/add")
    public String addNewTeacher(@Valid @ModelAttribute("teacherDTO") TeacherDTO teacherDTO, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return "/teacher/add";
        } else {
            Contract contract = new Contract();
            String password = EncrytedPasswordUtils.encrytePassword("123");
            AppUser appUser = new AppUser(teacherDTO.getMail(), password, true);
            iAppUserService.createAppUser(appUser);
            for (AppRole appRole : teacherDTO.getAppRoles()) {
                userRoleService.createUserRole(new UserRole(appRole, appUser));
            }
            BeanUtils.copyProperties(teacherDTO, contract);
            iContractService.createContract(contract);
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacherDTO, teacher);
            teacher.setContract(contract);
            teacher.setAppUser(appUser);
            teacher.setStatus(true);
            iTeacherService.addNewTeacher(teacher);
            return "redirect:/teacher";
        }
    }

    @GetMapping("/edit")
    public String showEdit(@RequestParam int id, Model model) {
        Teacher teacher = iTeacherService.findById(id);
        TeacherDTO teacherDTO = new TeacherDTO();
        BeanUtils.copyProperties(teacher, teacherDTO);
        model.addAttribute("teacherDTO", teacherDTO);
        return "/teacher/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute TeacherDTO teacherDTO, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return "/teacher/edit";
        } else {
            Teacher teacher1 = iTeacherService.findById(teacherDTO.getTeacherId());
            BeanUtils.copyProperties(teacherDTO, teacher1);
            iTeacherService.addNewTeacher(teacher1);
            return "redirect:/teacher";
        }

    }

    @GetMapping("/detail{id}")
    public String findById(@PathVariable() int id, Model model) {
        Teacher teacher = iTeacherService.findById(id);
        model.addAttribute("teacherDetail", teacher);
        return "redirect:/teacher";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        Teacher teacher = iTeacherService.findById(id);
        teacher.setStatus(false);
        iTeacherService.updateTeacher(teacher);
        return "redirect:/teacher";
    }

    @GetMapping("/user/detail")
    public String detail(Model model, Principal principal) {
                if (principal == null) {
            return "redirect:/login";
        }
        if (userRoleService.isUserAdmin(principal.getName())) {
            return "redirect:/403";
        }
        AppUser appUser = iAppUserService.findByUsername(principal.getName());
        Teacher teacher = iTeacherService.findTeacherByAppUser(appUser);
        model.addAttribute("teacher", teacher);
        return "teacher/detail";
    }
}
