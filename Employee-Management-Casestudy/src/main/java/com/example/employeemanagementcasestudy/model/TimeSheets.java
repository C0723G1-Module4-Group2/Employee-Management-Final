package com.example.employeemanagementcasestudy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int timeSheetId;
    @Column( name = "time_sheet_name",unique = true, nullable = false)
    private String timeSheetName;
    @Column(name = "start_time",nullable = false)
    private LocalTime startTime;
    @Column(name = "end_time",nullable = false)
    private LocalTime endTime;
    @JsonBackReference
    @OneToMany(mappedBy = "timeSheets")
    private Set<TeachingSchedule> teachingSchedules;

    public TimeSheets(int timeSheetId) {
        this.timeSheetId = timeSheetId;
    }
}
