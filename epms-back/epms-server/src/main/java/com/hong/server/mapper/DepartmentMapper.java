package com.hong.server.mapper;

import com.hong.server.pojo.Department;
import com.hong.server.pojo.RespBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 黄海宏
 * @since 2023-01-09
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 获取所有部门
     * @param i 父部门id
     * @return
     */
    List<Department> getAllDepartments(Integer i);

    /**
     * 添加部门
     * @param dep
     * @return
     */
    RespBean addDep(Department dep);

    /**
     * 删除部门
     * @param dep
     * @return
     */
    RespBean deleteDep(Department dep);
}
