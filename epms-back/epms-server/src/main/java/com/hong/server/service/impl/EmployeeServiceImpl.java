package com.hong.server.service.impl;

import com.hong.server.mapper.EmployeeMapper;
import com.hong.server.pojo.Employee;
import com.hong.server.pojo.RespBean;
import com.hong.server.pojo.RespPageBean;
import com.hong.server.service.IEmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 黄海宏
 * @since 2023-03-09
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {


    @Autowired
    private EmployeeMapper employeeMapper;


    /**
     * 获取所有员工（分页）
     *
     * @param currentPage
     * @param size
     * @param emp
     * @param beginDateScope
     * @return
     */
    @Override
    public RespPageBean getEmpByPage(Integer currentPage, Integer size, Employee emp, LocalDate[] beginDateScope) {
        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> empByPage = employeeMapper.getEmpByPage(page, emp, beginDateScope);
        RespPageBean respPageBean = new RespPageBean(empByPage.getTotal(), empByPage.getRecords());
        return respPageBean;
    }

    /**
     * 获取工号
     *
     * @return
     */
    @Override
    public RespBean maxWorkId() {
        List<Map<String, Object>> maps = employeeMapper.selectMaps(new QueryWrapper<Employee>().select("max(workID)"));

        return new RespBean(200,null,String.format("%08d", Integer.parseInt(maps.get(0).get("max(workID)").toString()) + 1));
    }

    /**
     * 添加员工
     * @param emp
     * @return
     */
    @Override
    public RespBean addEmp(Employee emp) {
        //处理合同期限，保留两位小数
        LocalDate beginContract = emp.getBeginContract();
        LocalDate endContract = emp.getEndContract();
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        DecimalFormat df = new DecimalFormat("##.00");
        emp.setContractTerm(Double.parseDouble(df.format(days / 365.00)));
        if (1 == employeeMapper.insert(emp)) {
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    /**
     * 查询员工
     * @param id
     * @return
     */
    @Override
    public List<Employee> getEmp(Integer id) {
        return employeeMapper.getEmp(id);
    }

    /**
     * 获取员工账套
     * @param currentPage
     * @param size
     * @return
     */
    @Override
    public RespPageBean getEmpWithSalary(Integer currentPage, Integer size) {
        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> employeeIPage = employeeMapper.getEmpWithSalary(page);
        RespPageBean respPageBean = new RespPageBean(employeeIPage.getTotal(), employeeIPage.getRecords());
        return respPageBean;
    }

}
