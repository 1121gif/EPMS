package com.hong.server.service;

import com.hong.server.pojo.EmployeeEc;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hong.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 黄海宏
 * @since 2023-01-09
 */
public interface IEmployeeEcService extends IService<EmployeeEc> {

    /**
     * 添加奖惩
     * @param employeeEc
     * @return
     */
    RespBean addEmpEc(EmployeeEc employeeEc);



    /**
     * 查询全部奖惩
     * @return
     */
    List<EmployeeEc> getAllEmpEc();

    /**
     * 更新奖惩
     * @param employeeEc
     * @return
     */
    RespBean updataEmpEc(EmployeeEc employeeEc);
}
