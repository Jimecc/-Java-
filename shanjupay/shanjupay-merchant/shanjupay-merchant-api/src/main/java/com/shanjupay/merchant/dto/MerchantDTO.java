package com.shanjupay.merchant.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Jim
 * @Description
 * @createTime 2023年03月05日
 */
@Data
@ApiModel(value="MerchantDTO",description = "商户信息")
public class MerchantDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("商户ID")
    private Long id;

    /**
     * 商户名称
     */
    @ApiModelProperty("企业名称")
    private String merchantName;

    /**
     * 企业编号
     */
    @ApiModelProperty("企业编号")
    private String merchantNo;

    /**
     * 企业地址
     */
    @ApiModelProperty("企业地址")
    private String merchantAddress;

    /**
     * 商户类型
     */
    @ApiModelProperty("企业类型")
    private String merchantType;

    /**
     * 营业执照（企业证明）
     */
    @ApiModelProperty("营业快照（企业证明）")
    private String businessLicensesImg;

    /**
     * 法人身份证正面照片
     */
    @ApiModelProperty("法人身份证正面照片")
    private String idCardFrontImg;

    /**
     * 法人身份证反面照片
     */
    @ApiModelProperty("法人身份证反面照片")
    private String idCardAfterImg;

    /**
     * 联系人姓名
     */
    @ApiModelProperty("联系人姓名")
    private String username;

    /**
     * 联系人手机号(关联统一账号)
     */
    @ApiModelProperty("联系人手机号(关联统一账号)")
    private String mobile;

    /**
     * 联系人地址
     */
    @ApiModelProperty("联系人地址")
    private String contactsAddress;

    /**
     * 审核状态 0-未申请,1-已申请待审核,2-审核通过,3-审核拒绝
     */
    @ApiModelProperty("审核状态 0-未申请,1-已申请待审核,2-审核通过,3-审核拒绝")
    private String auditStatus;

    /**
     * 租户ID,关联统一用户
     */
    @ApiModelProperty("租户ID,关联统一用户")
    private Long tenantId;

}
