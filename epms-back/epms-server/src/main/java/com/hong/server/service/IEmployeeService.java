package com.hong.server.service;

import com.hong.server.pojo.Employee;
import com.hong.server.pojo.RespBean;
import com.hong.server.pojo.RespPageBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 黄海宏
 * @since 2023-01-09
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * 获取所有员工（分页）
     * @param currentPage
     * @param size
     * @param emp
     * @param beginDateScope
     * @return
     */
    RespPageBean getEmpByPage(Integer currentPage, Integer size, Employee emp, LocalDate[] beginDateScope);

    /**
     * 获取工号
     * @return
     */
    RespBean maxWorkId();

    /**
     * 添加员工
     * @param emp
     * @return
     */
    RespBean addEmp(Employee emp);

    /**
     * 查询员工
     * @param id
     * @return
     */
    List<Employee> getEmp(Integer id);

    /**
     * 获取员工账套
     * @param currentPage
     * @param size
     * @return
     */
    RespPageBean getEmpWithSalary(Integer currentPage, Integer size);
}
