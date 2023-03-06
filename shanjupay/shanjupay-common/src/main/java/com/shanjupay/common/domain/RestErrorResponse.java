package com.shanjupay.common.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author Jim
 * @Description
 * @createTime 2023年03月06日
 */
@Data
@ApiModel(value="RestErrorResponse",description = "错误响应参数")
public class RestErrorResponse {

    private String errCode;
    private String errMessage;

    public RestErrorResponse(String errCode,String errMessage){
        this.errCode = errCode;
        this.errMessage = errMessage;
    }
}
