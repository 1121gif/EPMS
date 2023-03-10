package com.hong.server.utils;

/**
 * @author: 黄海宏~
 * @date: 2023/3/10 19:21
 * @version: 1.0
 * @description:
 */

import java.io.File;
import java.io.IOException;

/**
 * MySQL备份还原工具类
 *
 * @author Louis
 * @date Sep 21, 2018
 */
public class MySqlBackupRestoreUtils {

    /**
     * 备份数据库
     *
     * @param host             host地址，可以是本机也可以是远程
     * @param userName         数据库的用户名
     * @param password         数据库的密码
     * @param backupFolderPath 备份的路径
     * @param fileName         备份的文件名
     * @param database         需要备份的数据库的名称
     * @return
     * @throws IOException
     */
    public static boolean backup(String host, String userName, String password, String backupFolderPath, String fileName,
                                 String database) throws Exception {
        File backupFolderFile = new File(backupFolderPath);
        if (!backupFolderFile.exists()) {
            // 如果目录不存在则创建
            backupFolderFile.mkdirs();
        }
        if (!backupFolderPath.endsWith(File.separator) && !backupFolderPath.endsWith("/")) {
            backupFolderPath = backupFolderPath + File.separator;
        }
        // 拼接命令行的命令
        String backupFilePath = backupFolderPath + fileName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("cd C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin&")
                .append("mysqldump --opt")
                .append(" --add-drop-database")
                .append(" --add-drop-table")
                .append(" -h ").append(host)
                .append(" -u ").append(userName)
                .append(" --password=").append(password)
                .append(" " + database + ">")
                .append(backupFilePath)
                .append(".sql")
                .append(" --default-character-set=utf8 ");
//        System.out.println("1.数据库sql语句：  " + stringBuilder.toString());
        // 调用外部执行 exe 文件的 Java API
//        System.out.println("开始备份!");
        Process process = Runtime.getRuntime().exec(getCommand(stringBuilder.toString()));
//        System.out.println("命令执行完毕");
        if (process.waitFor() == 0) {
            // 0 表示线程正常终止
//            System.out.println("数据已经备份到 " + backupFilePath + " 文件中");
            return true;
        }
//        System.out.println("备份失败");
        System.out.println(process.getInputStream());
        System.out.println(process.getErrorStream());

        System.out.println(process.exitValue());
        byte[] bytes = new byte[process.getInputStream().available()];
        process.getInputStream().read(bytes);
        System.out.println(new String(bytes));
        process.getErrorStream().read(bytes);
        System.out.println(new String(bytes));
        return false;
    }

    /**
     * 还原数据库
     *
     * @param restoreFilePath 数据库备份的脚本路径
     * @param host            IP地址
     * @param database        数据库名称
     * @param userName        用户名
     * @param password        密码
     * @return
     */
    public static boolean restore(String restoreFilePath, String host, String userName, String password, String database)
            throws Exception {
        File restoreFile = new File(restoreFilePath);
        if (restoreFile.isDirectory()) {
            for (File file : restoreFile.listFiles()) {
                if (file.exists() && file.getPath().endsWith(".sql")) {
                    restoreFilePath = file.getAbsolutePath();
                    break;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("cd C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin&mysql -h").append(host).append(" -u").append(userName).append(" -p").append(password);
        stringBuilder.append(" ").append(database).append(" < ").append(restoreFilePath);
        System.out.println("命令："+stringBuilder.toString());
        try {
            Process process = Runtime.getRuntime().exec(getCommand(stringBuilder.toString()));
            if (process.waitFor() == 0) {
                System.out.println("数据已从 " + restoreFilePath + " 导入到数据库中");
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("数据从 " + restoreFilePath + " 导入到数据库失败");
        return false;
    }

    private static String[] getCommand(String command) {
        String os = System.getProperty("os.name");
        String shell = "/bin/bash";
        String c = "-c";
        if (os.toLowerCase().startsWith("win")) {
            shell = "cmd";
            c = "/c";
        }
        String[] cmd = {shell, c, command};
        return cmd;
    }
}
