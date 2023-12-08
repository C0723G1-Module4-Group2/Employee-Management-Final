package com.example.employeemanagementcasestudy.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class TeachingScheduleDto {
    private int teachingScheduleId;
    private int classId;
    private String className;
    private int timeSheetId;
    private LocalTime startTime;
    private LocalTime endTime;
    private int teacherId;
    private String teacherName;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean status;


    public TeachingScheduleDto() {
    }

    public TeachingScheduleDto(int teachingScheduleId, String className, LocalTime startTime, LocalTime endTime, String teacherName, LocalDate startDate, LocalDate endDate, boolean status) {
        this.teachingScheduleId = teachingScheduleId;
        this.className = className;
        this.startTime = startTime;
        this.endTime = endTime;
        this.teacherName = teacherName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public TeachingScheduleDto(int teachingScheduleId, int classId, String className, int timeSheetId, LocalTime startTime, LocalTime endTime, int teacherId, String teacherName, LocalDate startDate, LocalDate endDate, boolean status) {
        this.teachingScheduleId = teachingScheduleId;
        this.classId = classId;
        this.className = className;
        this.timeSheetId = timeSheetId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getTimeSheetId() {
        return timeSheetId;
    }

    public void setTimeSheetId(int timeSheetId) {
        this.timeSheetId = timeSheetId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getTeachingScheduleId() {
        return teachingScheduleId;
    }

    public void setTeachingScheduleId(int teachingScheduleId) {
        this.teachingScheduleId = teachingScheduleId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
