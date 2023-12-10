package com.example.employeemanagementcasestudy.service.impl;

import com.example.employeemanagementcasestudy.dto.TeacherDtoMap;
import com.example.employeemanagementcasestudy.dto.TeachingScheduleDto;
import com.example.employeemanagementcasestudy.dto.TimeSheetDto;
import com.example.employeemanagementcasestudy.model.Teacher;
import com.example.employeemanagementcasestudy.model.TeachingSchedule;
import com.example.employeemanagementcasestudy.repository.ITeachingScheduleRepository;
import com.example.employeemanagementcasestudy.service.IAppUserService;
import com.example.employeemanagementcasestudy.service.ITeacherService;
import com.example.employeemanagementcasestudy.service.ITeachingScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Service
public class TeachingScheduleService implements ITeachingScheduleService {
    @Autowired
    private ITeachingScheduleRepository teachingScheduleRepository;
    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private ITeacherService teacherService;

    @Override
    public void save(TeachingSchedule teachingSchedule) {
        teachingScheduleRepository.save(teachingSchedule);
    }

    @Override
    public void update(TeachingSchedule teachingSchedule) {
        teachingScheduleRepository.save(teachingSchedule);
    }

    @Override
    public List<TeachingSchedule> getAll() {
        return teachingScheduleRepository.findAll();
    }

    @Override
    public void delete(int id) {
        teachingScheduleRepository.delete(getById(id));
    }

    @Override
    public TeachingSchedule getById(int id) {
        return teachingScheduleRepository.findById(id).get();
    }

    @Override
    public List<TeachingScheduleDto> getAllDto() {
        return teachingScheduleRepository.getAll();
    }

    @Override
    public List<TimeSheetDto> getTimeSheetsFree(int classId, LocalDate startDate, LocalDate endDate) {
        return teachingScheduleRepository.getTimeSheetsFree(classId,startDate,endDate);
    }

    @Override
    public boolean isScheduleOverlapping(int classId, int teacherId, LocalDate newStartDate, LocalDate newEndDate) {
        return teachingScheduleRepository.isScheduleOverlapping(classId,teacherId,newStartDate,newEndDate);
    }

    @Override
    public List<TeacherDtoMap> getTeacherFree(LocalDate startDate, LocalDate endDate, int timeSheetId) {
        return teachingScheduleRepository.getTeacherFree(startDate,endDate,timeSheetId);
    }

    @Override
    public List<TimeSheetDto> getTimeSheetsFreeForUpdate(int classId, LocalDate startDate, LocalDate endDate, int teachingScheduleId) {
        return teachingScheduleRepository.getTimeSheetsFreeForUpdate(classId,startDate,endDate,teachingScheduleId);
    }

    @Override
    public List<TeacherDtoMap> getTeacherFreeForUpdate(int teachingScheduleId, LocalDate startDate, LocalDate endDate, int timeSheetId) {
        return teachingScheduleRepository.getTeacherFreeForUpdate(startDate,endDate,timeSheetId,teachingScheduleId);
    }

    @Override
    public List<TeachingScheduleDto> getAllScheduleByTeacher(String username) {
        return teachingScheduleRepository.getAllScheduleByTeacher(username);
    }



}
