package com.example.employeemanagementcasestudy.model;

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

public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(unique = true,nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy = "appUser")
    private Set<UserRole> userRoles;
    private boolean status;
    public AppUser(String username, String password, boolean status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }
}
