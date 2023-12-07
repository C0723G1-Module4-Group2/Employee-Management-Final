package com.example.employeemanagementcasestudy.service;


import com.example.employeemanagementcasestudy.model.AppUser;

import java.util.List;

public interface IAppUserService {
    List<AppUser> findAllAppUser();

    void createAppUser(AppUser appUser);

    AppUser findById(int id);

    void updateAppUser(AppUser appUser);

    Integer findUserIdByUsername(String username);
}
