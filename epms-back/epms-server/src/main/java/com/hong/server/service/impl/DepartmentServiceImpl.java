package com.hong.server.service.impl;

import com.hong.server.mapper.DepartmentMapper;
import com.hong.server.pojo.Department;
import com.hong.server.pojo.RespBean;
import com.hong.server.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 黄海宏
 * @since 2023-01-09
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 获取所有部门
     * @return
     */
    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartments(-1);
    }

    /**
     * 添加部门
     * @param dep
     * @return
     */
    @Override
    public RespBean addDep(Department dep) {
        dep.setEnabled(true);
        departmentMapper.addDep(dep);
        if (1 == dep.getResult()) {
            return RespBean.success("添加成功！",dep);
        }
        return RespBean.error("添加失败!");
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @Override
    public RespBean deleteDep(Integer id) {
        Department dep = new Department();
        dep.setId(id);
        departmentMapper.deleteDep(dep);
        if (-2 == dep.getResult()) {
            return RespBean.error("该部门下含有子部门，删除失败！");
        }
        if (-1 == dep.getResult()) {
            return RespBean.error("该部门下含有员工，删除失败！");
        }
        if (1 == dep.getResult()) {
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

}
