package com.example.employeemanagementcasestudy.controller;


import com.example.employeemanagementcasestudy.dto.TeacherDTO;
import com.example.employeemanagementcasestudy.dto.TeacherDto2;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Autowired
    private ITeachingScheduleService teachingScheduleService;

    @GetMapping("")
    public String showManagementTeacher(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "") String searchName,
                                        Model model) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("teacher_name").ascending());
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
    public String addNewTeacher(@Valid @ModelAttribute("teacherDTO") TeacherDTO teacherDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        AppUser appUserCheck = iAppUserService.findByUsername(teacherDTO.getMail());
        Contract contractCheck = iContractService.findByCode(teacherDTO.getContractCode());
        Teacher teacherCheck1 = iTeacherService.findTeacherByTeacherCode(teacherDTO.getTeacherCode());
        Teacher teacherCheck2 = iTeacherService.findTeacherByIdentificationCard(teacherDTO.getIdentificationCard());
        Teacher teacherCheck3 = iTeacherService.findTeacherByPhoneNumber(teacherDTO.getPhoneNumber());
        if (bindingResult.hasFieldErrors() || appUserCheck != null || contractCheck != null
        || teacherCheck1!=null || teacherCheck2 !=null || teacherCheck3!=null) {
            List<SalaryLevel> salaryLevelList = salaryLevelService.findAllSalaryLevel();
            model.addAttribute("salaryLevelList", salaryLevelList);
            List<AppRole> appRoleList = appRoleService.findAllAppRole();
            model.addAttribute("appRoleList", appRoleList);
            model.addAttribute("messageAppUser",
                    "Email " + teacherDTO.getMail() + " đã tồn tại");
            model.addAttribute("messageContract",
                    "Mã hợp đồng " + teacherDTO.getContractCode() + " đã tồn tại");
            model.addAttribute("messageTeacherCode",
                    "Mã giáo viên " + teacherDTO.getTeacherCode() + " đã tồn tại");
            model.addAttribute("messagePhoneNumber",
                    "Số điện thoại " + teacherDTO.getPhoneNumber() + " đã tồn tại");
            model.addAttribute("messageIdentificationCard",
                    "CMND " + teacherDTO.getIdentificationCard() + " đã tồn tại");
            return "/teacher/add";
        }
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

    @GetMapping("/edit")
    public String showEdit(@RequestParam int id, Model model) {
        Teacher teacher = iTeacherService.findById(id);
        TeacherDto2 teacherDto2 = new TeacherDto2();
        BeanUtils.copyProperties(teacher, teacherDto2);
        model.addAttribute("teacherDto2", teacherDto2);
        return "/teacher/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute TeacherDto2 teacherDto2, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return "/teacher/edit";
        } else {
            Teacher teacher1 = iTeacherService.findById(teacherDto2.getTeacherId());
            BeanUtils.copyProperties(teacherDto2, teacher1);
            iTeacherService.updateTeacher(teacher1);
            return "redirect:/teacher";
        }

    }

    @GetMapping("/detail{id}")
    public String findById(@PathVariable() int id, Model model) {
        Teacher teacher = iTeacherService.findById(id);
        model.addAttribute("teacherDetail", teacher);
        return "redirect:/teacher";
    }

    @Transactional
    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        Teacher teacher = iTeacherService.findById(id);
        teacher.setStatus(false);
        iTeacherService.updateTeacher(teacher);
        teachingScheduleService.deleteTeachingScheduleByTeacher(iTeacherService.findById(id));
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
