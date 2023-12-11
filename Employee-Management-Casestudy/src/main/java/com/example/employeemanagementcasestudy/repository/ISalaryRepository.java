package com.example.employeemanagementcasestudy.repository;


import com.example.employeemanagementcasestudy.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISalaryRepository extends JpaRepository<Teacher, Integer> {
//    @Query(nativeQuery = true, value = "select t.*, sl.coefficients_salary, sl.allowance\n" +
//            "from teacher t\n" +
//            "join contract c on c.contract_id = t.contract_id\n" +
//            "join salary_level sl on sl.salary_level_id  = c.salary_level_id where t.teacher_id = :id")
//    <SalaryDto>SalaryDto displaySalaryById(@Param("id") int idTeacher);

    @Query(nativeQuery = true, value = "select t.*, sl.coefficients_salary, sl.allowance\n" +
            "from teacher t\n" +
            "join contract c on c.contract_id = t.contract_id\n" +
            "join salary_level sl on sl.salary_level_id  = c.salary_level_id where t.status = 1")
    <SalaryDto>List<SalaryDto> getAll();

    @Query(nativeQuery = true, value = "select t.*, sl.coefficients_salary, sl.allowance\n" +
            "from teacher t\n" +
            "join contract c on c.contract_id = t.contract_id\n" +
            "join salary_level sl on sl.salary_level_id  = c.salary_level_id \n" +
            "where t.teacher_id = :id and t.status = 1")
    <SalaryDto>List<SalaryDto> getSalaryById(@Param("id") int teacherId);
}
