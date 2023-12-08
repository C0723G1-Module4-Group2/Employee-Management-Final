package com.example.employeemanagementcasestudy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teaching_schedule", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"time_sheet_id", "class_id", "start_date", "end_date"}),
        @UniqueConstraint(columnNames = {"time_sheet_id", "teacher_id", "start_date", "end_date"})
})
public class TeachingSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teachingScheduleId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "time_sheet_id",nullable = false)
    private TimeSheets timeSheets;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "class_id",nullable = false)
    private Classes classes;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "teacher_id",nullable = false)
    private Teacher teacher;
    @Column( name = "start_date",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="end_date",nullable = false)
    private LocalDate endDate;
    private boolean status;
}
