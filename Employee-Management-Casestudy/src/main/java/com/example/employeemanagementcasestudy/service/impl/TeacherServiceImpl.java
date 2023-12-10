package com.example.employeemanagementcasestudy.service.impl;


import com.example.employeemanagementcasestudy.model.AppUser;
import com.example.employeemanagementcasestudy.model.Teacher;
import com.example.employeemanagementcasestudy.repository.ITeacherRepository;
import com.example.employeemanagementcasestudy.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService {
    @Autowired
    private ITeacherRepository iTeacherRepository;

    @Override
    public void addNewTeacher(Teacher teacher) {
        iTeacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(int id) {
        iTeacherRepository.deleteById(id);
    }

    @Override
    public List<Teacher> getAll() {
        return iTeacherRepository.findAll();
    }

    @Override
    public void editTeacher(Teacher teacher) {
        iTeacherRepository.save(teacher);
    }

    public Teacher findById(int id) {
        return iTeacherRepository.findById(id).get();
    }

    @Override
    public Page<Teacher> displayAllTeacher(String teacherName, Pageable pageable) {
        return iTeacherRepository.searchName("%" + teacherName + "%", pageable);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        if (iTeacherRepository.existsById(teacher.getTeacherId())) {
            iTeacherRepository.save(teacher);
        }
    }

    @Override
    public Teacher findTeacherByAppUser(AppUser appUser) {
        return iTeacherRepository.findTeacherByAppUser(appUser);
    }
}
