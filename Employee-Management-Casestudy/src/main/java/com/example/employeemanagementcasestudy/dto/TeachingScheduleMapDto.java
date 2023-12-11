package com.example.employeemanagementcasestudy.dto;

import java.time.LocalDate;

public class TeachingScheduleMapDto {
    private int timeSheetId;
    private int classId;
    private int teacherId;
    private LocalDate startDate;
    private LocalDate endDate;

    public TeachingScheduleMapDto(int timeSheetId, int classId, int teacherId, LocalDate startDate, LocalDate endDate) {
        this.timeSheetId = timeSheetId;
        this.classId = classId;
        this.teacherId = teacherId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public TeachingScheduleMapDto() {
    }

    public int getTimeSheetId() {
        return timeSheetId;
    }

    public void setTimeSheetId(int timeSheetId) {
        this.timeSheetId = timeSheetId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
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
}
