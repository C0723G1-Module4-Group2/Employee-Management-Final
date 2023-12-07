package com.example.employeemanagementcasestudy.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teacher", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "contract_id"})
})
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teacherId;
    @Column(nullable = false, unique = true)
    private String teacherCode;
    @Column(nullable = false)
    private String teacherName;
    @Column(nullable = false)
    private LocalDateTime dateOfBirth;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false, unique = true)
    private String mail;
    @Column(nullable = false)
    private boolean gender;
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    @Column(nullable = false, unique = true)
    private String identificationCard;
    @Column(nullable = false)
    private String basicSalary;
    @Column(nullable = false)
    private String warningCoefficient;
    private boolean status;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser appUser;
    @OneToOne
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    @JsonManagedReference
    @OneToMany(mappedBy = "teacher")
    private Set<TeachingSchedule> teachingSchedules;

    public Teacher(int teacherId) {
        this.teacherId = teacherId;
    }
}