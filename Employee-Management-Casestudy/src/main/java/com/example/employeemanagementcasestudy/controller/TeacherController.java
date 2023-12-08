package com.example.employeemanagementcasestudy.controller;


import com.example.employeemanagementcasestudy.dto.TeacherDTO;
import com.example.employeemanagementcasestudy.model.AppUser;
import com.example.employeemanagementcasestudy.model.Contract;
import com.example.employeemanagementcasestudy.model.Teacher;
import com.example.employeemanagementcasestudy.model.UserRole;
import com.example.employeemanagementcasestudy.service.IAppUserService;
import com.example.employeemanagementcasestudy.service.IContractService;
import com.example.employeemanagementcasestudy.service.ITeacherService;
import com.example.employeemanagementcasestudy.service.IUserRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller()
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private ITeacherService iTeacherService;
    @Autowired
    private IContractService iContractService;
    @Autowired
    private IAppUserService iAppUserService;
    @GetMapping("")
    public String showManagementTeacher(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue =  "") String searchName,
                                        Model model){
        Pageable pageable = PageRequest.of(page,1, Sort.by("teacher_name").ascending());
        Page<Teacher> teacherPage = iTeacherService.displayAllTeacher(searchName,pageable);
        model.addAttribute("teacherPage",teacherPage);
        model.addAttribute("searchName",searchName);
        model.addAttribute("teacher",new Teacher());

        return "/teacher/managementTeacher";
    }
    @GetMapping("/add")
    public String showFormTeacher(Model model){
        model.addAttribute("teacherDTO",new TeacherDTO());
        int sizeUser = iAppUserService.findAllAppUser().size();
        AppUser appUser = iAppUserService.findAllAppUser().get(sizeUser - 1);
        int sizeContract = iContractService.getAllContract().size();
        Contract contract = iContractService.getAllContract().get(sizeContract - 1);
        model.addAttribute("contract",contract);
        model.addAttribute("user",appUser);
        return "/teacher/add";
    }
    @PostMapping("/add")
    public String addNewTeacher(TeacherDTO teacherDTO){
        int salary = teacherDTO.getBasicSalary();
        teacherDTO.setBasicSalary(salary);
       Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherDTO,teacher);
        iTeacherService.addNewTeacher(teacher);
        return "redirect:/teacher";
    }
    @GetMapping("/edit{id}")
    public String showEdit(@PathVariable int id,Model model){
        Teacher teacher = iTeacherService.findById(id);
        model.addAttribute("teacher",teacher);
        return "/teacher/managementTeacher";
    }
    @PostMapping("/edit")
    public String edit(int id,Teacher teacher){
        Teacher teacher1 = iTeacherService.findById(id);
        teacher1 = teacher;
        iTeacherService.addNewTeacher(teacher1);
        return "redirect:/teacher";
    }
    @GetMapping("/detail{id}")
    public String findById(@PathVariable() int id,Model model){
        Teacher teacher = iTeacherService.findById(id);
        model.addAttribute("teacherDetail",teacher);
        return "redirect:/teacher";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam() int id){
        iTeacherService.deleteTeacher(id);
        return "redirect:/teacher";
    }





}
