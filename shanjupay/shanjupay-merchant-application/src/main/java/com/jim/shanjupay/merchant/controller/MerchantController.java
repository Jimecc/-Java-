package com.jim.shanjupay.merchant.controller;

import com.jim.shanjupay.merchant.convert.MerchantConvert;
import com.jim.shanjupay.merchant.service.FileService;
import com.jim.shanjupay.merchant.service.SmsService;
import com.jim.shanjupay.merchant.vo.MerchantRegisterVO;
import com.shanjupay.merchant.api.MerchantService;
import com.shanjupay.merchant.dto.MerchantDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Jim
 * @Description
 * @createTime 2023年03月05日
 */

@RestController
public class MerchantController {

    @Autowired
    FileService fileService;

    @Autowired
    SmsService smsService;

    @Reference
    MerchantService merchantService;

    @ApiOperation("根据ID查询商户信息")
    @GetMapping("/merchant/{id}")
    public MerchantDTO queryMerchantByID(@PathVariable Long id){
        MerchantDTO merchantDTO = merchantService.queryMerchantByID(id);
        return merchantDTO;
    }

    @ApiOperation("获取手机验证码")
    @GetMapping("/sms")
    @ApiImplicitParam(value="手机号",name="phone",required = true,dataType = "string",paramType = "query")
    public String getSmsCode(@RequestParam("phone") String phone){
        return smsService.sendMsg(phone);
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @ApiOperation("商户注册")
    @ApiImplicitParam(value="商户注册信息",name="merchantRegisterVO",required = true,dataType = "MerchantRegisterVO",paramType = "body")
    @PostMapping("/merchants/register")
    public MerchantRegisterVO registerMerchant(@RequestBody MerchantRegisterVO merchantRegisterVO){
        smsService.checkVerifiyCode(merchantRegisterVO.getVerifiykey(),merchantRegisterVO.getVerifiyCode());
        MerchantDTO merchantDTO = MerchantConvert.INSTANCE.entity2dto(merchantRegisterVO);
        merchantService.createMerchant(merchantDTO);
        return merchantRegisterVO;
    }

    // 上传文件接口
    @ApiOperation("上传证件照")
    @PostMapping("/upload")
    public String upload(@ApiParam(value="上传证件照",required = true) @RequestParam("file") MultipartFile file) throws IOException {
        // 生成文件名称（要保证单一性）
        String originalFileName = file.getOriginalFilename();
        String suffix = originalFileName.substring(originalFileName.lastIndexOf('.')-1);
        String filename = UUID.randomUUID()+suffix;
        return fileService.upload(file.getBytes(),filename);
    }

}
