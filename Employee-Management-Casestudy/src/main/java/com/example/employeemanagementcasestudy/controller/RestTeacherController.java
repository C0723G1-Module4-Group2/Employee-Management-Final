package com.example.employeemanagementcasestudy.controller;


import com.example.employeemanagementcasestudy.model.Teacher;
import com.example.employeemanagementcasestudy.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api-teacher")
public class RestTeacherController {
    @Autowired
    private ITeacherService iTeacherService;

    @GetMapping("")
    public ResponseEntity<List<Teacher>> showListTeachers(){
        List<Teacher> teacherList = iTeacherService.getAll();
        if (teacherList.isEmpty()){
            return new ResponseEntity<>(teacherList,HttpStatus.OK);
        }
        return new ResponseEntity<>(teacherList,HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Teacher> teacherDetail(@PathVariable int id){
        try {
            Teacher teacher = iTeacherService.findById(id);
            return new ResponseEntity<>(teacher,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody Teacher teacher){
        if (teacher == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        iTeacherService.addNewTeacher(teacher);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Teacher teacher = iTeacherService.findById(id);
        if (teacher == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        iTeacherService.deleteTeacher(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PatchMapping("/teacher/{id}")
    public ResponseEntity<?> update(@PathVariable int id,
                                    @RequestBody Teacher teacher){
        Teacher teacher1 = iTeacherService.findById(id);
        if (teacher1 == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        iTeacherService.addNewTeacher(teacher);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
