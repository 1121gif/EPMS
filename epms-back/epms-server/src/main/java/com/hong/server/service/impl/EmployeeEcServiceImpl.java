package com.hong.server.service.impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.hong.server.mapper.EmployeeEcMapper;
import com.hong.server.mapper.EmployeeMapper;
import com.hong.server.pojo.Employee;
import com.hong.server.pojo.EmployeeEc;
import com.hong.server.pojo.RespBean;
import com.hong.server.service.IEmployeeEcService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class EmployeeEcServiceImpl extends ServiceImpl<EmployeeEcMapper, EmployeeEc> implements IEmployeeEcService {

    @Resource
    private EmployeeEcMapper employeeEcMapper;

    @Resource
    private EmployeeMapper employeeMapper;


    @Override
    public RespBean addEmpEc(EmployeeEc employeeEc) {
        if (employeeEc==null){
          throw new RuntimeException("写点东西把");
        }
        Employee employee = employeeMapper.selectById(employeeEc.getEid());
        if (employee==null){
            return RespBean.error("没这人");
        }
        int emec = employeeEcMapper.insert(employeeEc);
        if (emec==1){
            return RespBean.success("success",employeeEc.getEid());
        }
        return RespBean.error("error");
    }

    @Override
    public List<EmployeeEc> getAllEmpEc() {
        return employeeEcMapper.selectList(null);
    }

    @Override
    public RespBean updataEmpEc(EmployeeEc employeeEc) {
        if (employeeEc==null){
            throw new RuntimeException("写点东西把");
        }
        if (1 == employeeEcMapper.updateById(employeeEc)){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }


}
