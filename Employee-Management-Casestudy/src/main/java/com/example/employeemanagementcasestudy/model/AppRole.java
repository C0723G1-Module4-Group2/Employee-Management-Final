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

public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @Column(name = "Role_Name", length = 30, nullable = false,unique = true)
    private String roleName;
    @OneToMany(mappedBy = "appRole")
    private Set<UserRole> userRoles;
    private boolean status;
}
