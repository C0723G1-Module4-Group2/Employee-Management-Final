package com.example.employeemanagementcasestudy.service.impl;


import com.example.employeemanagementcasestudy.model.AppRole;
import com.example.employeemanagementcasestudy.repository.IAppRoleRepository;
import com.example.employeemanagementcasestudy.service.IAppRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AppRoleService implements IAppRoleService {
    @Autowired
    private IAppRoleRepository appRoleRepository;

    @Override
    public List<AppRole> findAllAppRole() {
        return appRoleRepository.findAll();
    }

    @Override
    public AppRole findAppRoleById(int id) {
        return appRoleRepository.findById(id).get();
    }

    @Override
    public void createAppRole(AppRole appRole) {
        appRoleRepository.save(appRole);
    }

    @Override
    public void updateAppRole(AppRole appRole) {
        if (appRoleRepository.existsById(appRole.getRoleId())) {
            appRoleRepository.save(appRole);
        }
    }

    @Override
    public AppRole findAppRoleByRoleName(String roleName) {
        return appRoleRepository.findAppRoleByRoleName(roleName);
    }
}
