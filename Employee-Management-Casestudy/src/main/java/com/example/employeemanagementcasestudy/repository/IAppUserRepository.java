package com.example.employeemanagementcasestudy.repository;


import com.example.employeemanagementcasestudy.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAppUserRepository extends JpaRepository<AppUser, Integer> {
    @Query(value = "select user_id from app_user where username = :username", nativeQuery = true)
    Integer findUserIdByUsername(@Param("username") String username);
    AppUser findByUsername(String username);
    @Query(value = "select * from app_user where status = 1",nativeQuery = true)
    List<AppUser> findAllAppUser();
}
