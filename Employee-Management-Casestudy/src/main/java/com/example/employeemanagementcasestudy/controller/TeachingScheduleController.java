package com.example.employeemanagementcasestudy.controller;
import com.example.employeemanagementcasestudy.model.AppUser;
import com.example.employeemanagementcasestudy.model.Teacher;
import com.example.employeemanagementcasestudy.model.TeachingSchedule;
import com.example.employeemanagementcasestudy.service.IAppUserService;
import com.example.employeemanagementcasestudy.service.ITeacherService;
import com.example.employeemanagementcasestudy.service.ITeachingScheduleService;
import com.example.employeemanagementcasestudy.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/schedule")
public class TeachingScheduleController {
    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private ITeachingScheduleService teachingScheduleService;
    @Autowired
    private IUserRoleService userRoleService;


    @GetMapping("")
    public String showCalendar(Model model){
        return "teaching_schedule/list";
    }
    @GetMapping("/user")
    public String showCalendarForUser(Model model, Principal principal){
        if (principal == null) {
            return "redirect:/login";
        }
        if (userRoleService.isUserAdmin(principal.getName())) {
            return "redirect:/403";
        }
        return "teaching_schedule/listForTeacher";
    }
}
