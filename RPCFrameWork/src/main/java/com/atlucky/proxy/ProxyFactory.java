package com.atlucky.proxy;

import com.atlucky.common.Invocation;
import com.atlucky.common.URL;
import com.atlucky.loadbalance.LoadBalance;
import com.atlucky.protocol.http.HttpClient;
import com.atlucky.register.MapRemoteRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

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

                //  注册中心   服务发现
                List<URL> remoteRegister =
                        MapRemoteRegister.getRemoteRegister(classInterfaceClass.getName());

                //负载均衡
                URL url = LoadBalance.getRandom(remoteRegister);

                //服务发送 调用
                String result=null;
                try {
                    result = httpClient.send(url.getHostName(), url.getPort() , sayHello);
                    System.out.println(result);
                    return result;
                }catch (Exception e){
                    return "请求发生错误";
                }
            }
        });

        return (T) newProxyInstance;

    }
}
