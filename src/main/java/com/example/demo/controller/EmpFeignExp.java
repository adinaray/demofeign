package com.example.demo.controller;

import com.example.demo.model.Employees;
import com.example.demo.service.EmpServFeign;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.concurrent.TimeoutException;


@RestController
@RequestMapping("/feign")
public class EmpFeignExp {


    @Autowired
    private EmpServFeign empServFeign;

    private static final Logger logger = LoggerFactory.getLogger(EmpFeignExp.class);


    @GetMapping("empls")
    public List<Employees> getallEmp(){
        return empServFeign.getall();
    }



    @GetMapping("empl/{id}")
    @CircuitBreaker(name = "mainService", fallbackMethod="testFallBack")
    public Employees getById(@PathVariable(value = "id") Long employeeId) {
        return empServFeign.getById(employeeId);
    }

    public Employees testFallBack(@PathVariable(value = "id") Long employeeId,Throwable t){
        logger.error("Inside FallBack for getting Personaldetails by id"+ t.toString());

        Employees emp= new Employees();
        emp.setId(employeeId);
        emp.setFirstName("Adinarayana");
        emp.setLastName("Tangella");
        emp.setEmailId("adi@gamil.com");
         return emp;
    }
}
