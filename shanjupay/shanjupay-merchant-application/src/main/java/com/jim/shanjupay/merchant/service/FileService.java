package com.jim.shanjupay.merchant.service;

import com.shanjupay.common.domain.BusinessException;

/**
 * @author Jim
 * @Description
 * @createTime 2023年03月07日
 */
public interface FileService {

    public String upload(byte[] bytes,String fileName) throws BusinessException;


}
