package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
@RestController
public class EmployeeController {
    //在此处完成Employee API

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value="/employees",method= RequestMethod.GET)
    List<Employee> getAllEmployees(){
        return this.employeeRepository.findAll();
    }

    @RequestMapping(value="/employees/{field}",method= RequestMethod.GET)
    List<Employee> getEmployeesById(@PathVariable String field){
        try{
            long id=Long.parseLong(field);
            return Arrays.asList(this.employeeRepository.findById(id));
        }catch (Exception e){
            return this.employeeRepository.findByGender(field);
        }
    }

    @RequestMapping(value="/employees/page/{page}/pageSize/{pageSize}",method= RequestMethod.GET)
    Page<Employee> getPages(@PathVariable  int page, @PathVariable  int pageSize){
        return this.employeeRepository.findAll(new PageRequest(page, pageSize));
    }

//    @RequestMapping(value="/employees/{gender}",method= RequestMethod.GET)
//    List<Employee> getEmployeesByGender(@PathVariable  String gender){
//        return this.employeeRepository.findByGender(gender);
//    }

    @RequestMapping(value="/employees",method= RequestMethod.POST)
    String  addEmployee( Employee employee){
        try {
            this.employeeRepository.save(employee);
            return "保存数据成功！";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @Transactional
    @RequestMapping(value="/employees/{id}",method= RequestMethod.PUT)
   public String update(@PathVariable  long id,Employee employee){
        try{
            this.employeeRepository.updateById(id,employee.getName());
            return "更新数据成功！";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @Transactional
    @RequestMapping(value="/employees/{id}",method= RequestMethod.DELETE)
    public Employee deleteEmployee(@PathVariable  long id){
        Employee employee=this.employeeRepository.findById(id);
        this.employeeRepository.delete(employee);
        return employee;
    }
}
