package com.example.employeemanagementcasestudy.repository;


import com.example.employeemanagementcasestudy.model.AppUser;
import com.example.employeemanagementcasestudy.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacherRepository extends JpaRepository<Teacher, Integer> {
    @Query(nativeQuery = true, value = "select  * from teacher where teacher_name like :teacherName and status = 1")
    Page<Teacher> searchName(@Param("teacherName") String teacherName, Pageable pageable);

    Teacher findTeacherByAppUser(AppUser appUser);

    Teacher findTeacherByIdentificationCard(String identificationCard);

    Teacher findTeacherByTeacherCode(String teacherCode);

    Teacher findTeacherByPhoneNumber(String phoneNumber);

}
