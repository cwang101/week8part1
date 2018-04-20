package com.example.employee.restfulapi.repository;

import com.example.employee.restfulapi.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findAll();

    Company findById(long id);

    Page<Company> findAll(Pageable pageable);

    Company save(Company company);

    @Modifying
    @Query("update Company company set company.companyName=?2 ,company.employeesNumber=?3 where company.id=?1")
    int updateById(long id ,String companyName,int employeesNumber);

    void delete(Company company);


}
