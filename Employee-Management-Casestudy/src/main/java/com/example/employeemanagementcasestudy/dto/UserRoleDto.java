package com.example.employeemanagementcasestudy.dto;



import com.example.employeemanagementcasestudy.model.AppRole;

import java.util.List;

public class UserRoleDto {
    private int id;
    private int userId;
    private String username;
    private List<AppRole> appRoles;

    public UserRoleDto(String username, List<AppRole> appRoles) {
        this.username = username;
        this.appRoles = appRoles;
    }
    public UserRoleDto(int userId, String username, List<AppRole> appRoles) {
        this.userId = userId;
        this.username = username;
        this.appRoles = appRoles;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserRoleDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<AppRole> getAppRoles() {
        return appRoles;
    }

    public void setAppRoles(List<AppRole> appRoles) {
        this.appRoles = appRoles;
    }
}
