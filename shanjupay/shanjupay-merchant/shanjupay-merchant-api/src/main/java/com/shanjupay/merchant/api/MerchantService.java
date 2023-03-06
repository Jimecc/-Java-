package com.shanjupay.merchant.api;

import com.shanjupay.merchant.dto.MerchantDTO;

/**
 * @author Jim
 * @Description
 * @createTime 2023年03月05日
 */
public interface MerchantService {

    // 根据ID查询商户
    public MerchantDTO queryMerchantByID(Long id);

    // 注册商户服务接口，姐搜狐账号、密码、手机号，为了可扩展性使用MerchantDTO接收数据
}
