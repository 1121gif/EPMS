package com.hong.server.controller;


import com.hong.server.pojo.Department;
import com.hong.server.pojo.RespBean;
import com.hong.server.service.IDepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 黄海宏
 * @since 2023-01-09
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @ApiOperation(value = "获取所有部门")
    @GetMapping("/")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }


    @ApiOperation(value = "添加部门")
    @PostMapping("/")
    public RespBean addDep(@RequestBody Department dep) {
        return departmentService.addDep(dep);
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping("/{id}")
    public RespBean deleteDep(@PathVariable Integer id) {
        return departmentService.deleteDep(id);
    }
}
