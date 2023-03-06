package com.shanjupay.merchant.convert;

import com.shanjupay.merchant.dto.MerchantDTO;
import com.shanjupay.merchant.entity.Merchant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Jim
 * @Description 转换类 / 对象属性映射 / 定义DTO与ENTITY的转换规则
 * @createTime 2023年03月06日
 */
@Mapper
public interface MerchantConvert {

    // 转换类实例
    MerchantConvert INSTANCE = Mappers.getMapper(MerchantConvert.class);

    Merchant dto2entity(MerchantDTO merchantDTO);

    MerchantDTO entity2dto(Merchant merchant);

}
