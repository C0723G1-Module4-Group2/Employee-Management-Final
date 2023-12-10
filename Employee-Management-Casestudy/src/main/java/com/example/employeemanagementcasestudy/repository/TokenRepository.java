package com.example.employeemanagementcasestudy.repository;


;
import com.example.employeemanagementcasestudy.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<PasswordResetToken, Integer> {

	PasswordResetToken findByToken(String token);

}
