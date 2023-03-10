package com.hong.server.service.impl;

import com.hong.server.constants.BackupConstants;
import com.hong.server.service.BackupService;
import com.hong.server.utils.FileUtils;
import com.hong.server.utils.MySqlBackupRestoreUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 黄海宏~
 * @date: 2023/3/10 18:03
 * @version: 1.0
 * @description: 备份还原数据库
 */
@Service
public class BackupServiceImpl implements BackupService {
    @Override
    public List<Map<String, String>> queryBackup(){
        List<Map<String, String>> backupRecords = new ArrayList<>();
        File restoreFolderFile = new File(BackupConstants.RESTORE_FOLDER);
        if(restoreFolderFile.exists()) {
            for(File file:restoreFolderFile.listFiles()) {
                Map<String, String> backup = new HashMap<>();
                backup.put("title", file.getName());
                backupRecords.add(backup);
            }
        }
        // 按时间戳排序，新备份在前面
        backupRecords.sort((o1, o2) -> o2.get("title").compareTo(o1.get("title")));
        return backupRecords;
    }

    @Override
    public boolean backup(String name){
        String host = BackupConstants.HOST;
        String userName = BackupConstants.USER_NAME;
        String password = BackupConstants.PASSWORD;
        String database = BackupConstants.DATABASE;
        String backupFolderPath = BackupConstants.BACKUP_FOLDER + name + File.separator;
        try {
            boolean success = MySqlBackupRestoreUtils.backup(host, userName, password, backupFolderPath, name, database);
            if(!success) {
                System.out.println("数据备份失败");
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        System.out.println("数据备份成功！");
        return true;
    }

    @Override
    public boolean restore(String name){
        String host = BackupConstants.HOST;
        String userName = BackupConstants.USER_NAME;
        String password = BackupConstants.PASSWORD;
        String database = BackupConstants.DATABASE;
        String restoreFilePath = BackupConstants.RESTORE_FOLDER + name;
        try {
            MySqlBackupRestoreUtils.restore(restoreFilePath, host, userName, password, database);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean delBackup(String name){
        String restoreFilePath = BackupConstants.BACKUP_FOLDER + name;
        try {
            if(FileUtils.deleteFile(new File(restoreFilePath))){
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
