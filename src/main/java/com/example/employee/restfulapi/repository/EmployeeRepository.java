package com.example.employee.restfulapi.repository;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    List<Employee> findAll();


    Employee findById(long id);

    Page<Employee> findAll(Pageable pageable);


    List<Employee> findByGender(String gender);

    Employee save(Employee employee);


    @Modifying
    @Query("update Employee employee set employee.name=?2  where employee.id=?1")
    int updateById(long id ,String name );

    @Override
    void delete(Employee employee);



}
