package com.hong.server.utils;

import java.io.File;

/**
 * @author: 黄海宏~
 * @date: 2023/3/10 19:20
 * @version: 1.0
 * @description:
 */
public class FileUtils {
    /**
     * 递归删除文件
     * @param file
     * @return
     */
    public static boolean deleteFile(File file) {
        // 判断是否是一个目录, 不是的话跳过, 直接删除; 如果是一个目录, 先将其内容清空.
        if(file.isDirectory()) {
            // 获取子文件/目录
            File[] subFiles = file.listFiles();
            // 遍历该目录
            for (File subFile : subFiles) {
                // 递归调用删除该文件: 如果这是一个空目录或文件, 一次递归就可删除.
                // 如果这是一个非空目录, 多次递归清空其内容后再删除
                deleteFile(subFile);
            }
        }
        // 删除空目录或文件
        file.delete();
        return true;
    }
}
