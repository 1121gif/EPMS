package com.hong.server.controller;

import com.hong.server.pojo.RespBean;
import com.hong.server.service.BackupService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: 黄海宏~
 * @date: 2023/3/10 18:02
 * @version: 1.0
 * @description: 备份还原数据库
 */
@RestController
@RequestMapping("api")
public class BackupController {

    @Autowired
    private BackupService backupService;

    // 获取备份记录
    @ApiOperation("获取备份记录")
    @GetMapping(value = "/backup/backup")
    public ResponseEntity getBackup() {
        return new ResponseEntity(backupService.queryBackup(), HttpStatus.OK);
    }

    // 备份
    @ApiOperation("开始备份")
    @PostMapping(value = "/backup/backup")
    public RespBean backup(@Validated @RequestBody String name) {
        if (backupService.backup(name)) {
            return RespBean.success("备份成功");
        }
        return RespBean.error("备份失败！");
//        return new ResponseEntity(HttpStatus.CREATED);
    }

    // 还原
    @ApiOperation("还原")
    @PutMapping(value = "/backup/restore")
    public RespBean restore(@Validated @RequestBody String name) {
        if (backupService.restore(name)){
            return RespBean.success("数据库还原成功！");
        }
        return RespBean.error("数据库还原失败！");
//        return new ResponseEntity(HttpStatus.OK);
    }

    // 删除备份记录
    @ApiOperation("删除备份记录")
    @DeleteMapping(value = "/backup/del")
    public RespBean delBackup(@Validated @RequestBody String name) {
        if (backupService.delBackup(name)){
            return RespBean.success("删除备份成功！");
        }

        return RespBean.error("删除备份失败！");
//        return new ResponseEntity(HttpStatus.OK);
    }
}
