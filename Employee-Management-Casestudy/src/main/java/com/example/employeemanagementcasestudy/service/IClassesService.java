package com.example.employeemanagementcasestudy.service;

import com.example.employeemanagementcasestudy.model.Classes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClassesService {
    List<Classes> findAll();
    void addClass(Classes blog);

    Classes findById(int id);
    Classes findByName(String name);

    void remove(int id);

    Page<Classes> findAllPage(String name, Pageable pageable);
}
