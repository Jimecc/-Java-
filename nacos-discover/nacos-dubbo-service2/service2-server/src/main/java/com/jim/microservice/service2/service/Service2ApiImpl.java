package com.jim.microservice.service2.service;

import com.jim.microservice.service2.api.Service2Api;

/**
 * @author Jim
 * @Description 微服务接口
 * @createTime 2023年03月05日
 */
@org.apache.dubbo.config.annotation.Service // 不是Springboot的@Service，而是Dubbo的Service，有了该注解，就会暴露成一个网络的接口
public class Service2ApiImpl implements Service2Api {

    @Override
    public String dubboService2() {
        return "dubboService2";
    }
}
