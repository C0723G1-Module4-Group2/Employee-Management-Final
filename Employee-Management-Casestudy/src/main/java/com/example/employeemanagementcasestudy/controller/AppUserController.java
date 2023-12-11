package com.example.employeemanagementcasestudy.controller;

import com.example.employeemanagementcasestudy.dto.UserRoleDto;
import com.example.employeemanagementcasestudy.model.AppRole;
import com.example.employeemanagementcasestudy.model.AppUser;
import com.example.employeemanagementcasestudy.model.UserRole;
import com.example.employeemanagementcasestudy.service.IAppRoleService;
import com.example.employeemanagementcasestudy.service.IAppUserService;
import com.example.employeemanagementcasestudy.service.IUserRoleService;
import com.example.employeemanagementcasestudy.util.EncrytedPasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/app-user")
public class AppUserController {
    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private IAppRoleService appRoleService;
    @Autowired
    private IUserRoleService userRoleService;

//    @GetMapping
//    public String showList(Model model) {
//        List<AppUser> appUserList = appUserService.findAllAppUser();
//        model.addAttribute("appUserList", appUserList);
//        return "user/list";
//    }
@GetMapping
public String showList(Model model,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String name) {
    Pageable pageable = PageRequest.of(page, 10, Sort.by("username").descending());
    Page<AppUser> blogAppUser;
        blogAppUser = appUserService.findAllAppUser(name, pageable);
    model.addAttribute("blogAppUser", blogAppUser);
    model.addAttribute("name", name);
    return "user/list";
}

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        UserRoleDto userRoleDto = new UserRoleDto();
        List<AppRole> appRoleList = appRoleService.findAllAppRole();
        model.addAttribute("appRoleList", appRoleList);
        model.addAttribute("userRoleDto", userRoleDto);
        return "/user/create";
    }

    @PostMapping("/save")
    public String save(RedirectAttributes redirectAttributes, UserRoleDto userRoleDto) {
        if (appUserService.findUserIdByUsername(userRoleDto.getUsername()) != null) {
            redirectAttributes.addFlashAttribute("message",
                    "Tên " + userRoleDto.getUsername() + " đã tồn tại");
            return "redirect:/app-user/create";
        }
        String password = EncrytedPasswordUtils.encrytePassword("123");
        AppUser appUser = new AppUser(userRoleDto.getUsername(), password, true);
        appUserService.createAppUser(appUser);
        List<AppRole> appRoleList = userRoleDto.getAppRoles();
        for (AppRole appRole : appRoleList) {
            userRoleService.createUserRole(new UserRole(appRole, appUser));
        }
        redirectAttributes.addFlashAttribute("success", "Thêm mới thành công");
        return "redirect:/app-user";
    }

    @GetMapping("/edit")
    public String showFromEdit(Model model, @RequestParam int id, Principal principal) {
//        if (principal == null) {
//            return "redirect:/login";
//        }
//        if (!userRoleService.isUserAdmin(principal.getName())) {
//            return "redirect:/403";
//        }
        AppUser appUser = appUserService.findById(id);
        List<UserRole> userRoleList = userRoleService.displayUserRoleByAppUser(id);
        List<AppRole> appRoleList = new ArrayList<>();
        for (UserRole userRole : userRoleList) {
            appRoleList.add(userRole.getAppRole());
        }
        List<AppRole> appRoleList1 = appRoleService.findAllAppRole();
        model.addAttribute("appRoleList1", appRoleList1);
        UserRoleDto userRoleDto = new UserRoleDto(id, appUser.getUsername(), appRoleList);
        model.addAttribute("userRoleDto", userRoleDto);
        return "/user/edit";
    }

    @PostMapping("/update")
    public String updateRole(RedirectAttributes redirectAttributes, UserRoleDto userRoleDto) {
        AppUser appUser = appUserService.findById(userRoleDto.getUserId());
        if(appUserService.findByUsername(userRoleDto.getUsername()) == null ||
                appUserService.findByUsername(userRoleDto.getUsername()).getUserId() == userRoleDto.getUserId()){
            appUser.setUsername(userRoleDto.getUsername());
            appUserService.updateAppUser(appUser);
            List<AppRole> appRoleList = userRoleDto.getAppRoles();
            List<UserRole> userRoleList = userRoleService.displayUserRoleByAppUser(appUser.getUserId());
            for (UserRole userRole : userRoleList) {
                if (!appRoleList.contains(userRole.getAppRole())) {
                    userRoleService.deleteUserRole(userRole);
                }
            }
            for (AppRole appRole : appRoleList) {
                Integer id;
                id = userRoleService.findIdByRoleAndUser(appRole.getRoleId(), appUser.getUserId());
                if (id != null) {
                    UserRole userRole = new UserRole(id, appRole, appUser);
                    if (userRoleService.checkUserRole(userRole)) {
                        userRoleService.updateUserRole(userRole);
                    }
                } else {
                    userRoleService.createUserRole(new UserRole(appRole, appUser));
                }
            }
            redirectAttributes.addFlashAttribute("edit", "Chỉnh sửa thành công");
            return "redirect:/app-user";
        }else {
            redirectAttributes.addFlashAttribute("message",
                    "Email " + userRoleDto.getUsername() + " đã tồn tại");
            return "redirect:/app-user/edit?id="+userRoleDto.getUserId();
        }
    }

    @GetMapping("/delete")
    public String delete(RedirectAttributes redirectAttributes, @RequestParam int id, Principal principal) {
//        if (principal == null) {
//            return "redirect:/login";
//        }
//        if (!userRoleService.isUserAdmin(principal.getName())) {
//            return "redirect:/403";
//        }
        AppUser appUser = appUserService.findById(id);
        appUser.setStatus(false);
        List<UserRole> userRoleList = userRoleService.displayUserRoleByAppUser(id);
        for (UserRole userRole : userRoleList) {
            userRoleService.deleteUserRole(userRole);
        }
        appUserService.updateAppUser(appUser);
        redirectAttributes.addFlashAttribute("delete", "Xóa tài khoản thành công");
        return "redirect:/app-user";
    }
}
