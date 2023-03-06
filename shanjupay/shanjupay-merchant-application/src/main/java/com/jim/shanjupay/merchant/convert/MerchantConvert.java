package com.jim.shanjupay.merchant.convert;

import com.jim.shanjupay.merchant.vo.MerchantRegisterVO;
import com.shanjupay.merchant.dto.MerchantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Jim
 * @Description
 * @createTime 2023年03月06日
 */
@Mapper
public interface MerchantConvert {

    MerchantConvert INSTANCE = Mappers.getMapper(MerchantConvert.class);

    MerchantRegisterVO dto2vo(MerchantDTO merchantDTO);

    MerchantDTO entity2dto(MerchantRegisterVO merchantRegisterVO);
}
