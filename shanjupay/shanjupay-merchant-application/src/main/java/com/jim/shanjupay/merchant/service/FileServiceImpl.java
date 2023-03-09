package com.jim.shanjupay.merchant.service;

import com.shanjupay.common.domain.BusinessException;
import com.shanjupay.common.domain.CommonErrorCode;
import com.shanjupay.common.util.QiniuUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Jim
 * @Description 文件操作
 * @createTime 2023年03月07日
 */
@Service
public class FileServiceImpl implements FileService{

    @Value("${oss.qiniu.url")
    private String qiniu_url;

    @Value("${oss.qiniu.accessKey")
    private String qiniu_accessKey;

    @Value("${oss.qiniu.secretKey")
    private String qiniu_secretKey;

    @Value("${oss.qiniu.bucket")
    private String qiniu_bucket;

    /**
     * @Author: Jim
     * @Description: 上传文件
     * @param bytes 文件字节数组
     * @param fileName 文件名
     */
    // 上传文件
    @Override
    public String upload(byte[] bytes, String fileName) throws BusinessException {
        // String accessKey,String secretKey,String bucket,byte[] bytes,String filename
        try {
            QiniuUtils.upload2qiniu(qiniu_accessKey,qiniu_secretKey,qiniu_bucket,bytes,fileName);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new BusinessException(CommonErrorCode.E_100106);
        }
        return qiniu_url+"/"+fileName;
    }
}
