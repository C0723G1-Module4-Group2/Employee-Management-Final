package com.example.employeemanagementcasestudy.repository;


import com.example.employeemanagementcasestudy.model.AppUser;
import com.example.employeemanagementcasestudy.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRoleRepository extends JpaRepository<UserRole, Integer> {
    @Query(value = "select * from user_role where user_id = :id", nativeQuery = true)
    List<UserRole> findUserRoleByAppUser(@Param("id") int id);

    @Query(value = "select id from user_role where role_id = :roleId and user_id = :userId", nativeQuery = true)
    Integer findIdByRoleAndUser(@Param("roleId") int roleId, @Param("userId") int userId);

    List<UserRole> findByAppUser(AppUser appUser);

}
