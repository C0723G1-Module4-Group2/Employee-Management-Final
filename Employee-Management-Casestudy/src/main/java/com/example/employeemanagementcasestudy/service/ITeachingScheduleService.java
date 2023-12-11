package com.example.employeemanagementcasestudy.service;


import com.example.employeemanagementcasestudy.dto.TeacherDtoMap;
import com.example.employeemanagementcasestudy.dto.TeachingScheduleDto;
import com.example.employeemanagementcasestudy.dto.TimeSheetDto;
import com.example.employeemanagementcasestudy.model.Teacher;
import com.example.employeemanagementcasestudy.model.TeachingSchedule;
import org.springframework.data.repository.query.Param;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

public interface ITeachingScheduleService {
    void save(TeachingSchedule teachingSchedule);
    void update(TeachingSchedule teachingSchedule);
    List<TeachingSchedule> getAll();
    void delete(int id);
    TeachingSchedule getById(int id);
    List<TeachingScheduleDto> getAllDto() ;
    List<TimeSheetDto> getTimeSheetsFree(int classId, LocalDate startDate, LocalDate endDate) ;
    boolean isScheduleOverlapping(int classId,
                                  int teacherId,
                                  LocalDate newStartDate,
                                  LocalDate newEndDate);
    List<TeacherDtoMap> getTeacherFree(LocalDate startDate, LocalDate endDate, int timeSheetId) ;
    List<TimeSheetDto> getTimeSheetsFreeForUpdate(int classId,
                                                  LocalDate startDate,
                                                  LocalDate endDate,
                                                  int teachingScheduleId);
    List<TeacherDtoMap> getTeacherFreeForUpdate(int teachingScheduleId,
                                                LocalDate startDate,
                                                LocalDate endDate,
                                                int timeSheetId);
    List<TeachingScheduleDto> getAllScheduleByTeacher(String username);

}
