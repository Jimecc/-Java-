package com.jim.shanjupay.merchant;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jim
 * @Description
 * @createTime 2023年03月06日
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RestTemplateTest {

    @Autowired
    RestTemplate restTemplate;

    // 测试使用RestTemplate作为客户端向服务端发起请求
    @Test
    public void getHtml(){
        String url = "http://www.baidu.com";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);
        String res = responseEntity.getBody();
        System.out.println(res);
    }

    // 测试获取验证码
    @Test
    public void getSmsCode(){
        String url = "http://localhost:56085/sailing/generate?effectiveTime=600&name=sms";
        // 请求信息，传入Body Header
        Map<String,Object> body = new HashMap<>();
        body.put("mobile","123456");//mobile + 手机号
        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(body,httpHeader);
        ResponseEntity<Map> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Map.class);
        log.info("请求验证码服务得到相应{}"+ JSON.toJSONString(exchange));
        Map bodyMap = (Map)exchange.getBody();
        System.out.println(bodyMap);
        Map result = (Map) bodyMap.get("result");
        System.out.println(result);
        String key = (String) result.get("key");
        System.out.println(key);

    }
}
