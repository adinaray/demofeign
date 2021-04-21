package com.example.demo.service;

import com.example.demo.model.Employees;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.desktop.SystemSleepEvent;
import java.util.List;
/*feign.name=feignEmpService
feign.url=http://localhost:8082/api/v1/


@FeignClient(name="feignEmpService", url="http://localhost:8082/api/v1/")
@FeignClient(name="${feign.name}",url="${feign.url}")
 */
@FeignClient("school-employee-service")
public interface EmpServFeign {
    @GetMapping("employee/getAllEmp")
    List<Employees> getall();

    @GetMapping("employee/getEmp/{id}")
    Employees getById(@PathVariable("id") Long employeeId);






}


