package com.example.employeemanagementcasestudy.service.impl;
import com.example.employeemanagementcasestudy.model.Classes;
import com.example.employeemanagementcasestudy.repository.IClassesRepository;
import com.example.employeemanagementcasestudy.service.IClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClassesService implements IClassesService {
    @Autowired
    private IClassesRepository classesRepository;


    @Override
    public List<Classes> getAllClass() {
        return classesRepository.getAllClass();
    }

    @Override
    public void addClass(Classes newClass) {
        classesRepository.save(newClass);
    }

    @Override
    public Classes findById(int id) {
        return classesRepository.findById(id).get();
    }

    @Override
    public Classes findByName(String name) {
        return classesRepository.findByName(name);
    }

    @Override
    public void remove(int id) {
        classesRepository.deleteClass(id);
    }

    @Override
    public Page<Classes> findAllPage(String name, Pageable pageable) {
        return classesRepository.search("%" + name + "%", pageable);
    }
}
