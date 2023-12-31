package com.example.employeemanagementcasestudy.controller;


import com.example.employeemanagementcasestudy.service.IUserRoleService;
import com.example.employeemanagementcasestudy.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private IUserRoleService userRoleService;

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcomePage(Model model, Principal principal) {
//        model.addAttribute("title", "Welcome");
//        model.addAttribute("message", "This is welcome page!");
//        User loginedUser = (User) ((Authentication) principal).getPrincipal();
//        List<String> roles = WebUtils.Role(loginedUser);
        if (userRoleService.isUserAdmin(principal.getName())) {
            return "teaching_schedule/list";
        }
        return "teaching_schedule/listForTeacher";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model, Principal principal) {
        if (principal != null) {
            return "redirect:/";
        }
        return "login/loginPage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "redirect:/login";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);


            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> bạn không được phép truy cập vào trang web này!";
            model.addAttribute("message", message);

        }

        return "login/403Page";
    }

}
