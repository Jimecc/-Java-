package com.jim.nacos.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jim
 * @Description
 * @createTime 2023年03月05日
 */
@RestController
public class RestProviderController {

    @GetMapping("/service")
    public String service(){
        System.out.println("provider invoke");
        return "provider invoke";
    }
}
