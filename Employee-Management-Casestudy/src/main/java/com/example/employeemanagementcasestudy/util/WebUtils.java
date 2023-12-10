package com.example.employeemanagementcasestudy.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebUtils {

    public static String toString(User user) {
        StringBuilder sb = new StringBuilder();

        sb.append("UserName:").append(user.getUsername());

        Collection<GrantedAuthority> authorities = user.getAuthorities();
        if (authorities != null && !authorities.isEmpty()) {
            sb.append(" (");
            boolean first = true;
            for (GrantedAuthority a : authorities) {
                if (first) {
                    sb.append(a.getAuthority());
                    first = false;
                } else {
                    sb.append(", ").append(a.getAuthority());
                }
            }
            sb.append(")");
        }
        return sb.toString();
    }
    public static List<String> Role(User user) {
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        List<String> roles = new ArrayList<>();
        if (authorities != null && !authorities.isEmpty()) {
            for (GrantedAuthority a : authorities) {
                roles.add(a.getAuthority());
            }
        }
        return roles;
    }

}