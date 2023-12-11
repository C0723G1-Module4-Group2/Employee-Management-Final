package com.example.employeemanagementcasestudy.controller;


import com.example.employeemanagementcasestudy.model.AppRole;
import com.example.employeemanagementcasestudy.service.IAppRoleService;
import com.example.employeemanagementcasestudy.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/app-role")
public class AppRoleController {
    @Autowired
    private IAppRoleService appRoleService;
    @Autowired
    private IUserRoleService userRoleService;

    @GetMapping
    public String showList(Model model) {
        List<AppRole> appRoleList = appRoleService.findAllAppRole();
        model.addAttribute("appRoleList", appRoleList);
        return "/role/list";
    }

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        AppRole appRole = new AppRole();
        model.addAttribute("appRole", appRole);
        return "/role/create";
    }

    @PostMapping("/save")
    public String save(RedirectAttributes redirectAttributes, AppRole appRole) {
        if (appRoleService.findAppRoleByRoleName(appRole.getRoleName()) != null) {
            redirectAttributes.addFlashAttribute("message",
                    "Tên " + appRole.getRoleName() + " đã tồn tại.Vui lòng nhập tên khác");
            return "redirect:/app-role/create";
        }
        appRoleService.createAppRole(new AppRole(appRole.getRoleName().toUpperCase(), true));
        redirectAttributes.addFlashAttribute("success", "Thêm mới thành công");
        return "redirect:/app-role";
    }

    @GetMapping("/edit")
    public String showFromEdit(Model model, @RequestParam int id, Principal principal) {
//        if (principal == null) {
//            return "redirect:/login";
//        }
//        if (!userRoleService.isUserAdmin(principal.getName())) {
//            return "redirect:/403";
//        }
        AppRole appRole = appRoleService.findAppRoleById(id);
        model.addAttribute("appRole", appRole);
        return "/role/edit";
    }

    @PostMapping("/update")
    public String updateRole(RedirectAttributes redirectAttributes, AppRole appRole) {
        AppRole appRole1 = appRoleService.findAppRoleByRoleName(appRole.getRoleName());
        if(appRole.getRoleId()==appRole1.getRoleId()){
            appRoleService.updateAppRole(appRole);
            redirectAttributes.addFlashAttribute("success", "Chỉnh sửa thành công");
            return "redirect:/app-role";
        }else {
            redirectAttributes.addFlashAttribute("message",
                    "Tên " + appRole.getRoleName() + " đã tồn tại.Vui lòng nhập tên khác");
            return "redirect:/app-role/edit?id="+appRole.getRoleId();
        }

    }
}
