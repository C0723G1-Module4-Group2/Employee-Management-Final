package com.example.employeemanagementcasestudy.repository;


import com.example.employeemanagementcasestudy.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppRoleRepository extends JpaRepository<AppRole, Integer> {
    AppRole findAppRoleByRoleName(String roleName);
}
