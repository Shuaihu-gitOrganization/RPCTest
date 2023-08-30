package com.atlucky;

import com.atlucky.common.Invocation;
import com.atlucky.protocol.http.HttpClient;
import com.atlucky.proxy.ProxyFactory;
import com.common.HelloService;
import lombok.extern.slf4j.Slf4j;

/**
 * @Date 2023/8/30 13:30
 * @Author XiaoHu
 * @Description
 **/
public class Consumer {
    public static void main(String[] args) {
        HelloService helloService= ProxyFactory.getProxy(HelloService.class);
        String sayHello = helloService.sayHello("atLucky");



    }
}
