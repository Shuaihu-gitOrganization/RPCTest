package com.atlucky;

import com.atlucky.common.Invocation;
import com.atlucky.protocol.http.HttpClient;
import com.atlucky.proxy.ProxyFactory;
import com.common.HelloService;
import com.common.UserService;
import domain.User;
import lombok.extern.slf4j.Slf4j;

/**
 * @Date 2023/8/30 13:30
 * @Author XiaoHu
 * @Description
 **/
public class Consumer {
    public static void main(String[] args) {
        //调用RPC中的动态代理
        HelloService helloService= ProxyFactory.getProxy(HelloService.class);

        String sayHello = helloService.sayHello("atLucky");

        UserService userService= ProxyFactory.getProxy(UserService.class);
        String user =userService.getUser(new User("张三", "26", "女"));


        System.out.println(sayHello);
       System.out.println(user);


    }
}
