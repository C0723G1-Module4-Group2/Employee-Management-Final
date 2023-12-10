package com.example.employeemanagementcasestudy.controller;

import com.example.employeemanagementcasestudy.dto.TeacherDtoMap;
import com.example.employeemanagementcasestudy.dto.TeachingScheduleDto;
import com.example.employeemanagementcasestudy.dto.TeachingScheduleMapDto;
import com.example.employeemanagementcasestudy.dto.TimeSheetDto;
import com.example.employeemanagementcasestudy.model.Classes;
import com.example.employeemanagementcasestudy.model.Teacher;
import com.example.employeemanagementcasestudy.model.TeachingSchedule;
import com.example.employeemanagementcasestudy.model.TimeSheets;
import com.example.employeemanagementcasestudy.service.ITeachingScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api-schedules")
public class RestTeachingScheduleController {
    @Autowired
    private ITeachingScheduleService teachingScheduleService;

    @GetMapping("")
    public ResponseEntity<List<TeachingScheduleDto>> getList() {
        List<TeachingScheduleDto> events = teachingScheduleService.getAllDto();
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/change-date")
    public ResponseEntity<List<TimeSheetDto>> getListDate(@RequestParam int classId,
                                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<TimeSheetDto> timeSheets = teachingScheduleService.getTimeSheetsFree(classId, startDate, endDate);
        if (timeSheets.isEmpty()) {
            return new ResponseEntity<>(timeSheets, HttpStatus.OK);
        }
        return new ResponseEntity<>(timeSheets, HttpStatus.OK);
    }

    @GetMapping("/change-sheets")
    public ResponseEntity<List<TeacherDtoMap>> getListTeacher(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                                              @RequestParam int timeSheetId) {
        List<TeacherDtoMap> teacherDtoMaps = teachingScheduleService.getTeacherFree(startDate,endDate, timeSheetId);
        if (teacherDtoMaps.isEmpty()) {
            return new ResponseEntity<>(teacherDtoMaps, HttpStatus.OK);
        }
        return new ResponseEntity<>(teacherDtoMaps, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSchedule(@RequestBody TeachingScheduleMapDto teachingScheduleMapDto) {
        TeachingSchedule teachingSchedule = new TeachingSchedule();
        teachingSchedule.setClasses(new Classes(teachingScheduleMapDto.getClassId()));
        ;
        teachingSchedule.setTimeSheets(new TimeSheets(teachingScheduleMapDto.getTimeSheetId()));
        teachingSchedule.setTeacher(new Teacher(teachingScheduleMapDto.getTeacherId()));
        teachingSchedule.setStartDate(teachingScheduleMapDto.getStartDate());
        teachingSchedule.setEndDate(teachingScheduleMapDto.getEndDate());
        teachingSchedule.setStatus(true);
        teachingScheduleService.save(teachingSchedule);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteSchedule(@RequestParam int teachingScheduleId) {
        TeachingSchedule teachingSchedule = teachingScheduleService.getById(teachingScheduleId);
        if (teachingSchedule == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        teachingScheduleService.delete(teachingScheduleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("")
    public ResponseEntity<?> updateSchedule(@RequestBody TeachingScheduleMapDto teachingScheduleMapDto,
                                            @RequestParam int teachingScheduleId) {
        TeachingSchedule teachingSchedule = teachingScheduleService.getById(teachingScheduleId);
        teachingSchedule.setTimeSheets(new TimeSheets(teachingScheduleMapDto.getTimeSheetId()));
        teachingSchedule.setClasses(new Classes(teachingScheduleMapDto.getClassId()));
        teachingSchedule.setTeacher(new Teacher(teachingScheduleMapDto.getTeacherId()));
        teachingSchedule.setStartDate(teachingScheduleMapDto.getStartDate());
        teachingSchedule.setEndDate(teachingScheduleMapDto.getEndDate());
        teachingScheduleService.update(teachingSchedule);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/change-update-date")
    public ResponseEntity<List<TimeSheetDto>> getListDateForUpdate(@RequestParam int classId,
                                                                   @RequestParam int teachingScheduleId,
                                                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<TimeSheetDto> timeSheets = teachingScheduleService.getTimeSheetsFreeForUpdate(classId, startDate, endDate, teachingScheduleId);
        if (timeSheets.isEmpty()) {
            return new ResponseEntity<>(timeSheets, HttpStatus.OK);
        }
        return new ResponseEntity<>(timeSheets, HttpStatus.OK);
    }

    @GetMapping("/change-update-sheets")
    public ResponseEntity<List<TeacherDtoMap>> getListTeacherForUpdate(@RequestParam int teachingScheduleId,
                                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                                                       @RequestParam int timeSheetId
    ) {
        List<TeacherDtoMap> teacherDtoMaps = teachingScheduleService.getTeacherFreeForUpdate(teachingScheduleId, startDate, endDate, timeSheetId);
        if (teacherDtoMaps.isEmpty()) {
            return new ResponseEntity<>(teacherDtoMaps, HttpStatus.OK);
        }
        return new ResponseEntity<>(teacherDtoMaps, HttpStatus.OK);
    }

    @GetMapping("/update")
    public ResponseEntity<TeachingSchedule> getInfoForUpdate(@RequestParam int teachingScheduleId) {
        TeachingSchedule teachingSchedule = teachingScheduleService.getById(teachingScheduleId);
        if (teachingSchedule == null) {
            return new ResponseEntity<>(teachingSchedule, HttpStatus.OK);
        }
        return new ResponseEntity<>(teachingSchedule, HttpStatus.OK);
    }
    @GetMapping("/user")
    public ResponseEntity<List<TeachingScheduleDto>> showCalendarForUser(Principal principal){

        List<TeachingScheduleDto> events = teachingScheduleService.getAllScheduleByTeacher(principal.getName());
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

}
