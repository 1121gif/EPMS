package com.hong.server.service.impl;

import com.hong.server.mapper.EmployeeEcMapper;
import com.hong.server.pojo.EmployeeEc;
import com.hong.server.service.IEmployeeEcService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
}
