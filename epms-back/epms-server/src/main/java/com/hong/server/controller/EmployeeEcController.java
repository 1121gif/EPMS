package com.hong.server.controller;


import com.hong.server.pojo.Employee;
import com.hong.server.pojo.EmployeeEc;
import com.hong.server.pojo.RespBean;
import com.hong.server.service.IEmployeeEcService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 黄海宏
 * @since 2023-01-09
 */
@RestController
@RequestMapping("/employee-ec")
public class EmployeeEcController {
    @Resource
    private IEmployeeEcService employeeEcService;

    /**
     * 增加员工奖惩记录
     * @param employeeEc
     * @return
     */
    @ApiOperation("增加员工奖惩记录")
    @PostMapping("/")
    public RespBean addEmpEc(@RequestBody EmployeeEc employeeEc){
        return employeeEcService.addEmpEc(employeeEc);
    }

    @ApiOperation("查询全部奖惩")
    @GetMapping("/")
    public List<EmployeeEc> getAllEmpEc(){
        return employeeEcService.getAllEmpEc();
    }

    @ApiOperation("修改员工奖惩")
    @PutMapping("/")
    public RespBean updataEmpEc(@RequestBody EmployeeEc employeeEc){
        return employeeEcService.updataEmpEc(employeeEc);
    }

}
