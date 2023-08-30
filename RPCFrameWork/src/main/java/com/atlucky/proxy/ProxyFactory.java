package com.atlucky.proxy;

import com.atlucky.common.Invocation;
import com.atlucky.protocol.http.HttpClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理工厂
 *
 * @Date 2023/8/30 15:39
 * @Author XiaoHu
 * @Description 动态代理调优
 **/
public class ProxyFactory {
    public static <T> T getProxy(Class classInterfaceClass){

        //读取用户配置
        Object newProxyInstance = Proxy.newProxyInstance(classInterfaceClass.getClassLoader(), new Class[]{classInterfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Invocation sayHello = new Invocation(classInterfaceClass.getName(),
                        method.getName(),
                        method.getParameterTypes(),
                        args);
                HttpClient httpClient = new HttpClient();
                String result = httpClient.send("localhost", 9999, sayHello);
                System.out.println(result);
                return result;
            }
        });

        return (T) newProxyInstance;

    }
}
