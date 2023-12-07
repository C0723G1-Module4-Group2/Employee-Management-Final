package com.example.employeemanagementcasestudy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classId;
    @Column(unique = true,nullable = false)
    private String className;
    private boolean status;
    @JsonBackReference
    @OneToMany(mappedBy = "classes")
    private Set<TeachingSchedule> teachingSchedules;

    public Classes(int classId) {
        this.classId = classId;
    }
}
