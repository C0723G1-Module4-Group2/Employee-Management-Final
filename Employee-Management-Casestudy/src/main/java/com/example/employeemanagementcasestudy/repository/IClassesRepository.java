package com.example.employeemanagementcasestudy.repository;


import com.example.employeemanagementcasestudy.model.Classes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IClassesRepository extends JpaRepository<Classes,Integer> {
    @Query(value = "select * from classes where class_name like:name and status=1", nativeQuery = true)
    Page<Classes> search(@Param("name") String name, Pageable pageable);
    @Query(value = "select * from classes where class_name =:name", nativeQuery = true)
    Classes findByName(@Param("name") String name);
    @Modifying
    @Query(value = "update classes set status=0 where class_id =:id", nativeQuery = true)
    void deleteClass(@Param("id") int id);
    @Query(value = "select * from classes where  status=1", nativeQuery = true)
    List<Classes> getAllClass();
}
