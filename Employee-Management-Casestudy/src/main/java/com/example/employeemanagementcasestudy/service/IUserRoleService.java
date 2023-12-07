package com.example.employeemanagementcasestudy.service;


import com.example.employeemanagementcasestudy.model.UserRole;

import java.util.List;

public interface IUserRoleService {
    void createUserRole(UserRole userRole);

    List<UserRole> displayAllUserRole();

    List<UserRole> displayUserRoleByAppUser(int id);

    void updateUserRole(UserRole userRole);

    void deleteUserRole(UserRole userRole);
    boolean checkUserRole(UserRole userRole);
    Integer findIdByRoleAndUser(int roleId, int userId);
    boolean isUserAdmin(String username);

}
