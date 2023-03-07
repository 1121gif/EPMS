package com.hong.server;

import com.hong.server.mapper.EmployeeMapper;
import com.hong.server.pojo.Department;
import com.hong.server.pojo.Employee;
import com.hong.server.service.IDepartmentService;
import com.hong.server.service.IMenuService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: haihong
 * @Create: 2023-03-09 12:23
 **/
@SpringBootTest
public class Mytest {

    @Autowired
    private IMenuService menuService;


    @Autowired
    private IDepartmentService departmentService;
    @Test
    public void irjwqoir() {

        List<Department> menus = departmentService.getAllDepartments();
        menus.forEach(menu -> System.out.println(menu));
        try {
            System.out.println(new ObjectMapper().writeValueAsString(menus));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void testEmpMappergetEmp() {
        List<Employee> emps = employeeMapper.getEmp(null);
        emps.forEach(emp -> System.out.println(emp));

    }

}
