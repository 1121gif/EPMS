package com.hong.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description: 分页公共返回对象
 * @Author: 黄海宏
 * @Create: 2023-02-24 18:04
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPageBean {
    /**
     * 总条数
     */
    private Long total;
    /**
     * 数据 List
     */
    private List<?> data;

}
