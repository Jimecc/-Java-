package com.jim.shanjupay.merchant.service;

import com.alibaba.fastjson.JSON;
import com.shanjupay.common.domain.BusinessException;
import com.shanjupay.common.domain.CommonErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jim
 * @Description
 * @createTime 2023年03月06日
 */
@Service
@Slf4j
public class SmsServiceImpl implements SmsService{

    @Value("${sms.url}")
    private String url;
    @Value("${sms.effectiveTime}")
    private String effectiveTime;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String sendMsg(String phone) {
        // 向验证码服务发送验证码
        // url
        String sms_url = url+"/generate?name=sms&effectiveTime="+effectiveTime;

        // 请求信息，传入Body Header
        Map<String,Object> body = new HashMap<>();
        body.put("mobile",phone);//mobile + 手机号
        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(body,httpHeader);
        ResponseEntity<Map> exchange = null;
        Map bodyMap = null;
        try {
            exchange = restTemplate.exchange(sms_url, HttpMethod.POST, httpEntity, Map.class);
            log.info("请求验证码服务得到相应{}"+ JSON.toJSONString(exchange));
            bodyMap = (Map)exchange.getBody();
        }catch (RestClientException e){
            e.printStackTrace();
            throw new RuntimeException("验证码请求失败");
        }
        if(bodyMap == null || bodyMap.get("result") == null){
            throw new RuntimeException("发送验证码失败");
        }
        Map result = (Map) bodyMap.get("result");
        String key = (String) result.get("key");
        log.info("得到发送验证码的KEY:"+key);
        return key;
    }

    @Override
    public void checkVerifiyCode(String verifiyKey, String verifiyCode) throws BusinessException {
        //校验验证码的url
        String check_url = url+"/verify?name=sms&verificationCode="+verifiyCode+"&verificationKey="+verifiyKey;

        Map bodyMap = null;
        try {
            //使用restTemplate请求验证码服务
            ResponseEntity<Map> exchange = restTemplate.exchange(check_url, HttpMethod.POST, HttpEntity.EMPTY, Map.class);
            log.info("请求验证码服务，得到响应:{}", JSON.toJSONString(exchange));
            bodyMap = exchange.getBody();
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException(CommonErrorCode.E_100102);

        }
        if(bodyMap == null || bodyMap.get("result") == null || !(Boolean) bodyMap.get("result")){
            throw new BusinessException(CommonErrorCode.E_100102);
        }
    }
}
