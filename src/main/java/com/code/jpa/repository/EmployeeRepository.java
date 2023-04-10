package com.code.jpa.repository;

import com.code.jpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    void deleteAllByEmpName(String name);
    @Modifying
    @Query(value = "update Employee set emp_name = :empName, position = :position where emp_no = :empNo",nativeQuery = true)
    void updateByEmpNo(@Param("empName") String empName, @Param("position") String position, @Param("empNo") String empNo);

    List<Employee> findAllByEmpName (String name);
}
