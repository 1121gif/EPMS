package com.hong.server.controller;

import com.hong.server.pojo.Employee;
import com.hong.server.pojo.RespBean;
import com.hong.server.pojo.RespPageBean;
import com.hong.server.pojo.Salary;
import com.hong.server.service.IEmployeeService;
import com.hong.server.service.ISalaryService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 员工账套
 * @Author: 黄海宏
 * @Create: 2023-02-17 12:27
 **/
@RestController
@RequestMapping("/salary/sobcfg")
public class SalarySobCfgController {

    @Autowired
    private ISalaryService salaryService;

    @Autowired
    private IEmployeeService employeeService;

    @ApiOperation("获取所有工资账套")
    @GetMapping("/salaries")
    public List<Salary> getAllSalaries() {
        return salaryService.list();
    }

    @ApiOperation("获取所有员工账套")
    @GetMapping("/")
    public RespPageBean getEmpWithSalary(@RequestParam(defaultValue = "1") Integer currentPage,
                                         @RequestParam(defaultValue = "10") Integer size) {
        return employeeService.getEmpWithSalary(currentPage, size);
    }

    @ApiOperation("更新员工账套")
    @PutMapping("/")
    public RespBean updateEmpSalary(Integer eid,Integer sid) {
        if (employeeService.update(new UpdateWrapper<Employee>().set("salaryid", sid).eq("id", eid))) {
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }
}
