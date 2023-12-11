package com.example.employeemanagementcasestudy.service.impl;


import com.example.employeemanagementcasestudy.model.UserRole;
import com.example.employeemanagementcasestudy.repository.IUserRoleRepository;
import com.example.employeemanagementcasestudy.service.IAppRoleService;
import com.example.employeemanagementcasestudy.service.IAppUserService;
import com.example.employeemanagementcasestudy.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService implements IUserRoleService {
    @Autowired
    private IUserRoleRepository userRoleRepository;
    @Autowired
    private IAppRoleService appRoleService;
    @Autowired
    private IAppUserService appUserService;

    @Override
    public void createUserRole(UserRole userRole) {
        userRoleRepository.save(userRole);
    }

    @Override
    public List<UserRole> displayAllUserRole() {
        return userRoleRepository.findAll();
    }

    @Override
    public List<UserRole> displayUserRoleByAppUser(int id) {
        return userRoleRepository.findUserRoleByAppUser(id);
    }

    @Override
    public void updateUserRole(UserRole userRole) {
        if (userRoleRepository.existsById(userRole.getId())) {
            userRoleRepository.save(userRole);
        }
    }

    @Override
    public void deleteUserRole(UserRole userRole) {
        if (userRoleRepository.existsById(userRole.getId())) {
            userRoleRepository.delete(userRole);
        }
    }

    @Override
    public boolean checkUserRole(UserRole userRole) {
        return userRoleRepository.existsById(userRole.getId());
    }

    @Override
    public Integer findIdByRoleAndUser(int roleId, int userId) {
        return userRoleRepository.findIdByRoleAndUser(roleId, userId);
    }
    @Override
    public boolean isUserAdmin(String username) {
        Integer id = appUserService.findUserIdByUsername(username);
        List<UserRole> userRoleList = displayUserRoleByAppUser(id);
        for (UserRole userRole : userRoleList) {
            if (userRole.getAppRole().getRoleName().equals("ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }
}
