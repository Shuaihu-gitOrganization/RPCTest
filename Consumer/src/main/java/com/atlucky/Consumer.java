package com.atlucky;

import com.atlucky.common.Invocation;
import com.atlucky.protocol.http.HttpClient;
import com.common.HelloService;
import lombok.extern.slf4j.Slf4j;

/**
 * @Date 2023/8/30 13:30
 * @Author XiaoHu
 * @Description
 **/
public class Consumer {
    public static void main(String[] args) {
        /*HelloService helloService= null ;
        String sayHello = helloService.sayHello("atLucky");*/

        Invocation sayHello = new Invocation(HelloService.class.getName(),
                "sayHello",
                new Class[]{String.class},
                new Object[]{"张三"});
        HttpClient httpClient = new HttpClient();
        String result = httpClient.send("localhost", 9999, sayHello);
        System.out.println(result);

    }
}
