package com.hong.server.controller;

import com.hong.server.pojo.Department;
import com.hong.server.service.IDepartmentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 测试controller
 * @Author: 黄海宏
 * @Create: 2023-01-09 20:10
 **/
@RestController
@Api(tags = "测试controller 接口")
public class HelloController {

    @Autowired
    private IDepartmentService departmentService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/employee/basic/hello")
    public String hello2() {
        return "/employee/basic/hello";
    }

    @GetMapping("/employee/advanced/hello")
    public String hello3() {
        return "/employee/advanced/hello";
    }

    @GetMapping("/test/department")
    public List<Department> getAlldepartments() {
       return departmentService.getAllDepartments();
    }
}
