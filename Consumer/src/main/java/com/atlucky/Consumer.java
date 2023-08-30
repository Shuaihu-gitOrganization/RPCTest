package com.atlucky;

import com.common.HelloService;

/**
 * @Date 2023/8/30 13:30
 * @Author XiaoHu
 * @Description
 **/
public class Consumer {
    public static void main(String[] args) {
        HelloService helloService= null ;
        String sayHello = helloService.sayHello("atLucky");

    }
}
