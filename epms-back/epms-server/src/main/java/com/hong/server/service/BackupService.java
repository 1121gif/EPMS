package com.hong.server.service;

import java.util.List;
import java.util.Map;

/**
 * @author: 黄海宏~
 * @date: 2023/3/10 18:03
 * @version: 1.0
 * @description: 备份还原数据库
 */
public interface BackupService {
    List<Map<String, String>> queryBackup();
    boolean backup(String name);
    boolean restore(String name);
    boolean delBackup(String name);
}

