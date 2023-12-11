package com.example.employeemanagementcasestudy.repository;

import com.example.employeemanagementcasestudy.dto.ContractDto1;
import com.example.employeemanagementcasestudy.model.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IContractRepository extends JpaRepository<Contract,Integer> {
    @Query(value = "select * from contract where contract_code like:code and status =1", nativeQuery = true)
    Page<Contract> search(@Param("code") String code, Pageable pageable);
    @Query(value = "select * from contract where contract_code =:code and status=1", nativeQuery = true)
    Contract findByCode(@Param("code") String code);
    @Query(value = "select c.contract_id as contractId,c.contract_code as contractCode,c.start_date as startDate,c.end_date as endDate,s.coefficients_salary as coefficientsSalary from contract c join salary_level s on s.salary_level_id = c.salary_level_id where contract_code like:code and status = 1",nativeQuery = true)
    Page<ContractDto1> getContract(@Param("code") String code, Pageable pageable);
    @Modifying
    @Query(value = "update contract set status=0 where contract_id =:id ", nativeQuery = true)
    void deleteContract(@Param("id") int id);
}
