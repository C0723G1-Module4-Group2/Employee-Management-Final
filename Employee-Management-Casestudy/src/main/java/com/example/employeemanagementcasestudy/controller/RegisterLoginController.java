package com.example.employeemanagementcasestudy.controller;


import com.example.employeemanagementcasestudy.model.AppUser;
import com.example.employeemanagementcasestudy.model.PasswordResetToken;
import com.example.employeemanagementcasestudy.repository.TokenRepository;
import com.example.employeemanagementcasestudy.service.IAppUserService;
import com.example.employeemanagementcasestudy.service.impl.UserDetailsServiceImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class RegisterLoginController {

    @Autowired
    UserDetailsServiceImpl2 userDetailsServiceImpl2;

    @Autowired
    IAppUserService appUserService;
    @Autowired
    TokenRepository tokenRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/forgotPassword")
    public String forgotPassword() {
        return "/login/forgotPassword";
    }

    @PostMapping("/forgotPassword")
    public String forgotPassordProcess(@RequestParam String email, RedirectAttributes redirectAttributes) {
        String output = "";
        AppUser appUser1 = appUserService.findByUsername(email);
        if (appUser1 != null) {
            output = userDetailsServiceImpl2.sendEmail(appUser1);
        } else {
            redirectAttributes.addFlashAttribute("mail", email);
            redirectAttributes.addFlashAttribute("message",
                    "Mail của bạn không có trong hệ thống .Vui lòng kiểm tra lại");
            return "redirect:/forgotPassword";
        }
        if (output.equals("success")) {
            return "redirect:/forgotPassword?success";
        }
        return "redirect:/login?error";
    }

    @GetMapping("/resetPassword/{token}")
    public String resetPasswordForm(@PathVariable String token, Model model) {
        PasswordResetToken reset = tokenRepository.findByToken(token);
        if (reset != null && userDetailsServiceImpl2.hasExipred(reset.getExpiryDateTime())) {
            model.addAttribute("email", reset.getAppUser().getUsername());
            return "/login/resetPassword";
        }
        return "redirect:/forgotPassword?error";
    }

    @PostMapping("/resetPassword")
    public String passwordResetProcess(@RequestParam String password, @RequestParam String email, RedirectAttributes redirectAttributes, Principal principal) {
        AppUser appUser1 = appUserService.findByUsername(email);
        if (appUser1 != null) {
            appUser1.setPassword(passwordEncoder.encode(password));
            appUserService.updateAppUser(appUser1);
        }
        if(principal==null){
            redirectAttributes.addFlashAttribute("success", "Đổi mật khẩu thành công");
            return "redirect:/login";
        }else {
            redirectAttributes.addFlashAttribute("success", "Đổi mật khẩu thành công");
            return "redirect:/";
        }
    }

    @GetMapping("/resetPassword")
    public String resetPasswordFormUser(Model model, @RequestParam String email) {
        model.addAttribute("email", email);
        return "/login/resetPassword";
    }

}
