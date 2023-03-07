package com.hong.server.service.impl;

import com.hong.server.mapper.OplogMapper;
import com.hong.server.pojo.Oplog;
import com.hong.server.service.IOplogService;
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
public class OplogServiceImpl extends ServiceImpl<OplogMapper, Oplog> implements IOplogService {

}
