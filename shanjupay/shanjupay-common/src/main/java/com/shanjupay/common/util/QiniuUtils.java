package com.shanjupay.common.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * @author Jim
 * @Description 7牛云工具类
 * @createTime 2023年03月06日
 */
public class QiniuUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(QiniuUtils.class);
    // 测试文件上传
    public static String accessKey = "du7-kjA5jK1CVXHKg0QN4MclfeZf2G15iMlV2ZOR";
    public static String secretKey = "Kb-gT5jNW5qwhyabZmiN-yfaOxyJkieXA0F210vc";
    public static String bucket = "jimeccshanjupay";

    /**
     * @Author: Jim
     * @Description: 文件上传工具方法
     * @param accessKey 秘钥key
     * @param secretKey 秘钥key
     * @param bucket    bucket
     * @param bytes     文件字节数组
     * @param filename  文件名称/与七牛云保持一致
     */
    public static void upload2qiniu(String accessKey, String secretKey, String bucket, byte[] bytes, String filename) throws RuntimeException{
        Configuration cfg = new Configuration(Region.huadong());
//...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        FileInputStream fileInputStream = null;
        try {
            byte[] uploadBytes = bytes;
//            byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
            // 认证
            Auth auth = Auth.create(accessKey, secretKey);
            // 认证结束后拿到令牌
            String upToken = auth.uploadToken(bucket);
            String key = filename;
            try {
                // KEY 建议生成一个不重复的名称
                Response response = uploadManager.put(uploadBytes, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                LOGGER.error("上传文件到七牛失败："+ex.getMessage());
                try {
                    LOGGER.error(r.bodyString());
                } catch (QiniuException ex2) {
                    throw  ex2;
                }
                throw new RuntimeException(r.bodyString());
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    private static void uploadFile() throws IOException {
//...指定存储区域

        Configuration cfg = new Configuration(Region.huadong());
//...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        FileInputStream fileInputStream = null;
        String key = null;
        try {
            String filepath = "/Users/hsk/Documents/GitHub/-Java-/shanjupay/notes/morestart.png";
            fileInputStream = new FileInputStream(new File(filepath));
            byte[] uploadBytes = IOUtils.toByteArray(fileInputStream);
//            byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
            // 认证
            Auth auth = Auth.create(accessKey, secretKey);
            // 认证结束后拿到令牌
            String upToken = auth.uploadToken(bucket);
            try {
                // KEY 建议生成一个不重复的名称
                key = UUID.randomUUID().toString();
                Response response = uploadManager.put(uploadBytes, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (UnsupportedEncodingException ex) {
            //ignore
        }
    }


    private static void getdownloadurl() throws UnsupportedEncodingException{
        String fileName = "电话截图.JPG";
        String domainOfBucket = "http://rr3rixg9g.hd-bkt.clouddn.com";
        String encodedFileName = URLEncoder.encode(fileName,"utf-8").replace("+","%20");
        String publicUrl = String.format("%s/%s",domainOfBucket,encodedFileName);
        Auth auth = Auth.create(accessKey,secretKey);
        long expireInSeconds = 3600;
        String finalUrl = auth.privateDownloadUrl(publicUrl,expireInSeconds);
        System.out.println(finalUrl);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        QiniuUtils.getdownloadurl();
    }
}
