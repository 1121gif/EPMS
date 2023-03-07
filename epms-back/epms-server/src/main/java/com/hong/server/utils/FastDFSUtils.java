package com.hong.server.utils;

import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: 黄海宏~
 * @date: 2023/3/5 22:14
 * @version: 1.0
 * @description: FastDFS工具类
 */
public class FastDFSUtils {

    private static Logger logger = LoggerFactory.getLogger(FastDFSUtils.class);

    //初始化Tracker的信息
    static{
        try {
            //获取了fastdfs配置文件
            String filePath=new ClassPathResource("fdfs_client.conf").getPath();
            //加载tracker的配置信息并初始化
            ClientGlobal.init(filePath);
        } catch (Exception e) {
            logger.error("初始化FastDFS失败",e.getMessage());
        }
    }

    /**
     * 文件上传
     * @param file 要上传的文件
     * @return 返回文件存入的位置（组名+虚拟磁盘路径+文件名）
     */
    public static String[] upload(MultipartFile file){
        String name = file.getOriginalFilename();
        logger.info("文件名：",name);
        StorageClient storageClient = null;
        String[] uploadResults=null;
        try {
            //获取storage客户端
            storageClient = getStorageClient();
            //三个参数分别为 文件内容byte[]、文件扩展名、文件信息列表
            uploadResults = storageClient.upload_file(file.getBytes(),name.substring(name.lastIndexOf(".")+1),null);

        } catch (Exception e) {
            logger.error("上传文件失败",e.getMessage());
        }
        if (null == uploadResults){
            logger.error("上传失败",storageClient.getErrorCode());
        }
        return uploadResults;
    }

    public static FileInfo getFileInfo(String groupName,String remoteFileName){
        StorageClient storageClient = null;

        try {
            storageClient = getStorageClient();
            FileInfo fileInfo = storageClient.get_file_info(groupName, remoteFileName);
            return fileInfo;
        } catch (Exception e) {
            logger.error("文件信息获取失败",e.getMessage());
        }
        return null;
    }

    /**
     * 下载文件
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static InputStream downFile(String groupName,String remoteFileName){
        StorageClient storageClient = null;

        try {
            storageClient = getStorageClient();
            byte[] fileByte = storageClient.download_file(groupName, groupName);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(fileByte);
            return inputStream;
        } catch (Exception e) {
            logger.error("文件下载失败",e.getMessage());
        }
        return null;
    }

    /**
     * 删除文件
     * @param groupName
     * @param remoteFileName
     */
    public static void deleteFile(String groupName,String remoteFileName){
        StorageClient storageClient = null;

        try {
            storageClient = getStorageClient();
            storageClient.delete_file(groupName,remoteFileName);
        } catch (Exception e) {
            logger.error("文件删除失败",e.getMessage());
        }
    }

    //获取Tracker服务器对象
    private static TrackerServer getTrackerServer() throws Exception{
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        return trackerServer;
    }

    //获取StorageClient客户端
    private static StorageClient getStorageClient() throws Exception{
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient;
    }

    /**
     * 获取文件路径
     * @return
     */
    public static String getTrackerUrl(){
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = null;
        StorageServer storeStorage = null;
        try {
            trackerServer = trackerClient.getTrackerServer();
            storeStorage = trackerClient.getStoreStorage(trackerServer);
        } catch (Exception e) {
            logger.error("文件路径获取失败",e.getMessage());
        }
        return "http://"+storeStorage.getInetSocketAddress().getHostString()+":8080/";
    }
}
