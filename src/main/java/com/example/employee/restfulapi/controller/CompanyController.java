package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.CompanyRepository;
import com.example.employee.restfulapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
@RestController
public class CompanyController {
    //在此处完成Company API

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @RequestMapping(value="/companies",method= RequestMethod.GET)
    List<Company>  getAllCompanies(){
        return this.companyRepository.findAll();
    }
    @RequestMapping(value="/companies/{id}",method= RequestMethod.GET)
    Company getCompanyById(@PathVariable  long id){
        return this.companyRepository.findById(id);
    }
    @RequestMapping(value="/companies/{id}/employees",method= RequestMethod.GET)
    List<Employee> getAllEmployeesByCompanyId(@PathVariable  long id){
        return this.companyRepository.findById(id).getEmployees();
    }
    @RequestMapping(value="/companies/page/{page}/pageSize/{pageSize}",method= RequestMethod.GET)
    Page<Company> getPages(@PathVariable  int page,@PathVariable  int pageSize){
        return this.companyRepository.findAll(new PageRequest(page, pageSize));
    }


    @RequestMapping(value="/companies",method= RequestMethod.POST)
    String  addCompanies( Company company){
        try {
            this.companyRepository.save(company);
            return "保存数据成功！";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @Transactional
    @RequestMapping(value="/companies/{id}",method= RequestMethod.PUT)
    public String update(@PathVariable  long id,Company company){
        try{
            this.companyRepository.updateById(id,company.getCompanyName(),company.getEmployeesNumber());
            return "更新数据成功！";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @Transactional
    @RequestMapping(value="/companies/{id}",method= RequestMethod.DELETE)
   public Company deleteEmployeesUnderCompany(@PathVariable  long id){
        Company company=this.companyRepository.findById(id);
        this.companyRepository.delete(company);
//        List<Employee> employees =company.getEmployees();
//        employeeRepository.delete(employees);
        return company;
    }
}
