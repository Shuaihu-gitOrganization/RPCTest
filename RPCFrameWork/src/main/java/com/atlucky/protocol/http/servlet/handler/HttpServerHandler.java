package com.atlucky.protocol.http.servlet.handler;

import com.atlucky.common.Invocation;
import com.atlucky.register.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Date 2023/8/30 14:18
 * @Author XiaoHu
 * @Description
 **/
public class HttpServerHandler {
    public void handler(HttpServletRequest req, HttpServletResponse resp) {

        //请求处理-->处理接口、方法、方法参数
        try {
            Invocation invocation  =(Invocation) new ObjectInputStream( req.getInputStream()).readObject();
            String interfaceName = invocation.getInterfaceName();
            //根据消费者应用传过来的接口名通过反射机制拿到接口实现类
            Class classImpl = LocalRegister.getRegister(interfaceName,"1.0");
            //根据实现类反射拿到实现方法 动态代理
            Method method = classImpl.getMethod(invocation.getMethodName(), invocation.getParameterTypes());
            //执行具体的方法 动态代理
            String  result = (String) method.invoke(classImpl.newInstance(), invocation.getParameters());

            //写到响应请求之中
            IOUtils.write(result,resp.getOutputStream());


        } catch (IOException e ) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
