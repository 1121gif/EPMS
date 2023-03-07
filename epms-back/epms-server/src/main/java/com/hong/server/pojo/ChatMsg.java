package com.hong.server.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Description: 消息类
 * @Author: 黄海宏
 * @Create: 2023-01-28 17:36
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChatMsg {

    private String from;

    private String to;

    private String content;

    private LocalDateTime date;

    private String fromNickName;

}
