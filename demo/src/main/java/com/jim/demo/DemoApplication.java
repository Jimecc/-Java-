package com.jim.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class DemoApplication {


    public static void main(String[] args) {
        Demo demo1 = new Demo();
        Demo demo2 = new Demo();

        JavaInterface javaInterface = new JavaInterfaceImpl();
        javaInterface.DemoDefault();

    }

}
