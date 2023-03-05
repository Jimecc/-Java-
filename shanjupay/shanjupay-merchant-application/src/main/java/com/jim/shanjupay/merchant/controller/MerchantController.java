package com.jim.shanjupay.merchant.controller;

import com.shanjupay.merchant.api.MerchantService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jim
 * @Description
 * @createTime 2023年03月05日
 */

@RestController
public class MerchantController {

    @Reference
    MerchantService merchantService;

    @GetMapping("/merchant/{id}")
    public 

}
