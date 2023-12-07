package com.example.employeemanagementcasestudy.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_role",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"role_id","user_id"})
})
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private AppRole appRole;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser appUser;
    public UserRole(AppRole appRole, AppUser appUser) {
        this.appRole = appRole;
        this.appUser = appUser;
    }
}