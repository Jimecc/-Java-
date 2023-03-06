package com.jim.shanjupay.merchant.service;

/**
 * @author Jim
 * @Description 发送验证码
 * @createTime 2023年03月06日
 */
public interface SmsService {

    /**
     * @Author: Jim
     * @Description: 发送手机验证码
     * @param phone 手机号
     */
    String sendMsg(String phone);

    void checkVerifiyCode(String verifiyKey,String verifiyCode);
}
