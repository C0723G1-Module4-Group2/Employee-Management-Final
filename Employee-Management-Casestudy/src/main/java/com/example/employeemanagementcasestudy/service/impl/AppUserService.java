package com.example.employeemanagementcasestudy.service.impl;


import com.example.employeemanagementcasestudy.model.AppUser;
import com.example.employeemanagementcasestudy.repository.IAppUserRepository;
import com.example.employeemanagementcasestudy.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AppUserService implements IAppUserService {
    @Autowired
    private IAppUserRepository appUserRepository;

    @Override
    public List<AppUser> findAllAppUser() {
        return appUserRepository.findAllAppUser();
    }

    @Override
    public void createAppUser(AppUser appUser) {
        appUserRepository.save(appUser);
    }

    @Override
    public AppUser findById(int id) {
        return appUserRepository.findById(id).get();
    }

    @Override
    public void updateAppUser(AppUser appUser) {
        if (appUserRepository.existsById(appUser.getUserId())) {
            appUserRepository.save(appUser);
        }
    }

    @Override
    public Integer findUserIdByUsername(String username) {
        return appUserRepository.findUserIdByUsername(username);
    }

    @Override
    public Page<AppUser> findAllAppUser( String username,Pageable pageable) {
        return appUserRepository.search("%"+username+"%",pageable);
    }


}
