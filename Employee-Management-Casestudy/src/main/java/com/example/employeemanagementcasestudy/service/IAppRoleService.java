package com.example.employeemanagementcasestudy.service;


import com.example.employeemanagementcasestudy.model.AppRole;

import java.util.List;

public interface IAppRoleService {
    List<AppRole> findAllAppRole();

    AppRole findAppRoleById(int id);

    void createAppRole(AppRole appRole);

    void updateAppRole(AppRole appRole);
    AppRole findAppRoleByRoleName(String roleName);
}
