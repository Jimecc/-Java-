package com.jim.shanjupay.merchant.convert;

import com.jim.shanjupay.merchant.vo.MerchantRegisterVO;
import com.shanjupay.merchant.dto.MerchantDTO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-06T23:11:58+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
public class MerchantConvertImpl implements MerchantConvert {

    @Override
    public MerchantRegisterVO dto2vo(MerchantDTO merchantDTO) {
        if ( merchantDTO == null ) {
            return null;
        }

        MerchantRegisterVO merchantRegisterVO = new MerchantRegisterVO();

        merchantRegisterVO.setMobile( merchantDTO.getMobile() );
        merchantRegisterVO.setUsername( merchantDTO.getUsername() );

        return merchantRegisterVO;
    }

    @Override
    public MerchantDTO entity2dto(MerchantRegisterVO merchantRegisterVO) {
        if ( merchantRegisterVO == null ) {
            return null;
        }

        MerchantDTO merchantDTO = new MerchantDTO();

        merchantDTO.setUsername( merchantRegisterVO.getUsername() );
        merchantDTO.setMobile( merchantRegisterVO.getMobile() );

        return merchantDTO;
    }
}
