package com.jim.nacos.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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

    @GetMapping(value = "/service1")
    public String service1(){
        RestTemplate restTemplate = new RestTemplate();
        // 远程调用方法
        ServiceInstance serviceInstance = loadBalancerClient.choose(serviceId);
        URI uri = serviceInstance.getUri();
        String providerResult = restTemplate.getForObject(uri+"/service",String.class);
        return "consumer incoke | "+providerResult;
    }

    @GetMapping(value = "/service")
    public String service(){
        RestTemplate restTemplate = new RestTemplate();
        // 远程调用方法
        String providerResult = restTemplate.getForObject("http://"+providerAddress+"/service",String.class);
        return "consumer incoke | "+providerResult;
    }



}
