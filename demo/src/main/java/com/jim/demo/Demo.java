package com.jim.demo;

/**
 * @author Jim
 * @Description
 * @createTime 2023年03月09日
 */
public class Demo {

    public Demo(){
        System.out.println("构造方法");
    }

    static{
        System.out.println("静态代码块");
    }

    {
        System.out.println("代码块");
    }
}
