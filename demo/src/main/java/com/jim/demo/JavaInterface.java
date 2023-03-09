package com.jim.demo;

/**
 * @author Jim
 * @Description
 * @createTime 2023年03月09日
 */
public interface JavaInterface {

    public static void DemoStatic(){
        System.out.println("静态方法");
    }

    public default void DemoDefault(){
        System.out.println("默认方法");
    }

}
