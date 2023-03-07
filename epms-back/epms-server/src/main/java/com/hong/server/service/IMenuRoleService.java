package com.hong.server.service;

import com.hong.server.pojo.MenuRole;
import com.hong.server.pojo.RespBean;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 黄海宏
 * @since 2023-01-09
 */
public interface IMenuRoleService extends IService<MenuRole> {


    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    RespBean updateMenuRole(Integer rid, Integer[] mids);

}
