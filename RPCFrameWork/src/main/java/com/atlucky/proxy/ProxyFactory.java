package com.atlucky.proxy;

import com.atlucky.common.Invocation;
import com.atlucky.common.URL;
import com.atlucky.loadbalance.LoadBalance;
import com.atlucky.protocol.http.HttpClient;
import com.atlucky.register.MapRemoteRegister;
import org.apache.tomcat.util.buf.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
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
                //Mock
                String mock = System.getProperty("mock");

                if (mock !=null && mock.startsWith("return:")){
                    String result = mock.replace("return:", "");
                    return result;
                }


                Invocation sayHello = new Invocation(classInterfaceClass.getName(),
                        method.getName(),
                        method.getParameterTypes(),
                        args);//可传版本
                HttpClient httpClient = new HttpClient();

                //  注册中心   服务发现
                List<URL> remoteRegister =
                        MapRemoteRegister.getRemoteRegister(classInterfaceClass.getName());

                ArrayList<URL> invocatedUrls = new ArrayList<>();

                //服务发送 调用
                String result = null;
                int max = 3;
                while (max > 0) {//服务重试
                    //负载均衡
                    invocatedUrls.remove(classInterfaceClass);
                    URL url = LoadBalance.getRandom(remoteRegister);
                    invocatedUrls.add(url);
                    try {

                        //RPC协议
                        result = httpClient.send(url.getHostName(), url.getPort(), sayHello);
                        System.out.println(result);

                    } catch (Exception e) {
                        if (max-- != 0) continue;
                        //error-callback = HelloServiceErrorBack
                        //容错
                        return "请求发生错误,服务端连接超时";
                    }
                }
                return result;
            }
        });


        return (T) newProxyInstance;

    }
}
