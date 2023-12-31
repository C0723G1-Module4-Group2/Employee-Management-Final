package com.example.employeemanagementcasestudy.service;


import com.example.employeemanagementcasestudy.model.AppUser;
import com.example.employeemanagementcasestudy.model.Contract;
import com.example.employeemanagementcasestudy.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITeacherService {
    public void addNewTeacher(Teacher teacher);

    public void deleteTeacher(int id);

    public List<Teacher> getAll();

    public void editTeacher(Teacher teacher);

    public Teacher findById(int id);

    Page<Teacher> displayAllTeacher(String teacherName, Pageable pageable);

    void updateTeacher(Teacher teacher);

    Teacher findTeacherByAppUser(AppUser appUser);
    Teacher findTeacherByIdentificationCard(String identificationCard);

    Teacher findTeacherByTeacherCode(String teacherCode);

    Teacher findTeacherByPhoneNumber(String phoneNumber);
    Teacher findTeacherByContract(Contract contract);
}
