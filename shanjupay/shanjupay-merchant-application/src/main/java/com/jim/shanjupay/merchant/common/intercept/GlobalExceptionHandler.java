package com.jim.shanjupay.merchant.common.intercept;

import com.shanjupay.common.domain.BusinessException;
import com.shanjupay.common.domain.CommonErrorCode;
import com.shanjupay.common.domain.ErrorCode;
import com.shanjupay.common.domain.RestErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jim
 * @Description 全局异常处理
 * @createTime 2023年03月06日
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse processException(HttpServletResponse httpServletResponse,
                                              HttpServletRequest httpServletRequest,
                                              Exception e){
        // 解析异常信息
        // 如果是系统自定义异常，就直接取出ErrCode与ErrorMessage
        if(e instanceof BusinessException){
            LOGGER.info(e.getMessage(),e);
            BusinessException businessException = (BusinessException) e;
            ErrorCode errorCode = businessException.getErrorCode();
            int    code = errorCode.getCode();
            String desc = errorCode.getDesc();
            return new RestErrorResponse(String.valueOf(code),desc);
        }

        LOGGER.error("系统异常",e);
        // 不是系统定义异常，统一定义为99999异常
        return new RestErrorResponse(String.valueOf(CommonErrorCode.UNKOWN.getCode()),CommonErrorCode.UNKOWN.getDesc());
    }
}
