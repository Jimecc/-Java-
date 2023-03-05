package com.jim.nacos.consumer.controller;

import com.jim.microservice.service1.api.Service1Api;
import com.jim.microservice.service2.api.Service2Api;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @author Jim
 * @Description
 * @createTime 2023年03月05日
 */
@RestController
public class RestConsumerController {

    // 远程调用provider的方法
    @Value("${provider.address}")
    private String providerAddress;

    // 指定 Nacos 服务名
    String serviceId = "nacos-restful-provider";
    // 通过负载均衡找到地址：从服务发现中心拿到服务的列表，通过负载均衡的算法获取一个地址
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Reference
    Service2Api service2Api;

    @Reference
    Service1Api service1Api;

    @GetMapping(value = "/service3")
    public String service3(){
        //远程调用service1
        String providerResult = service1Api.dubboService1();
        return "consumer dubbo invoke |"+providerResult;
    }


    @GetMapping(value = "/service")
    public String service(){
        RestTemplate restTemplate = new RestTemplate();
        // 远程调用方法
        String providerResult = restTemplate.getForObject("http://"+providerAddress+"/service",String.class);
        return "consumer incoke | "+providerResult;
    }


    @GetMapping(value = "/service1")
    public String service1(){
        RestTemplate restTemplate = new RestTemplate();
        // 远程调用方法
        ServiceInstance serviceInstance = loadBalancerClient.choose(serviceId);
        URI uri = serviceInstance.getUri();
        String providerResult = restTemplate.getForObject(uri+"/service",String.class);
        return "consumer incoke | "+providerResult;
    }

    @GetMapping(value = "/service2")
    public String service2(){
        String providerResult = service2Api.dubboService2();
        return "consumer incoke | "+providerResult;
    }

    // 使用Nacos做配置
    @Value("${common.name}")
    private String common_name;

    @GetMapping("/common_name_value")
    public String getCommon_name(){
        return common_name;
    }

    @Autowired
    ConfigurableApplicationContext appicationContext;

    @GetMapping("/common_name_applicationContext")
    public String getValue(){
        return appicationContext.getEnvironment().getProperty("common.name");
    }

    @GetMapping("/commons1")
    public String Conmons1(){
        String name = appicationContext.getEnvironment().getProperty("common.name");
        String addr = appicationContext.getEnvironment().getProperty("common.addr");
        System.out.println(name+":"+addr);
        return name+":"+addr;
    }

    @GetMapping("/commons2")
    public String Conmons2(){
        String name = appicationContext.getEnvironment().getProperty("common.name");
        String addr = appicationContext.getEnvironment().getProperty("common.addr");
        System.out.println(name+":"+addr);
        return name+":"+addr;
    }

}
