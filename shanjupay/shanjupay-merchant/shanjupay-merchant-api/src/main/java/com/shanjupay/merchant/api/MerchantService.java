package com.shanjupay.merchant.api;

import com.shanjupay.merchant.dto.MerchantDTO;

/**
 * @author Jim
 * @Description
 * @createTime 2023年03月05日
 */
public interface MerchantService {

    // 根据ID查询商户
    public MerchantDTO queryMerchantBuID(Long id);
}
