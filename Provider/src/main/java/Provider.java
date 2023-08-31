import com.atlucky.common.URL;
import com.atlucky.protocol.http.HttpServer;
import com.atlucky.register.LocalRegister;
import com.atlucky.register.MapRemoteRegister;
import com.common.HelloService;
import service.implement.HelloServiceImpl;
import service.implement.HelloServiceImplV2;

import java.util.List;

/**
 * @Date 2023/8/30 13:44
 * @Author XiaoHu
 * @Description
 **/
public class Provider {
    public static void main(String[] args) {
        //启动 相关服务器  Tomcat 、Netty、Jetty 接收消费应用发来相关请求

        //读取用户的hostname、port配置
        //Spring——>Spring容器——>执行
        //本地注册
        LocalRegister.register(HelloService.class.getName(),"1.0" ,HelloServiceImpl.class);
        LocalRegister.register(HelloService.class.getName(),"2.0" , HelloServiceImplV2.class);


        //注册中心   服务注册
        URL url = new URL("localhost", 9999);
        MapRemoteRegister.remoteRegister(HelloService.class.getName(),url);

        //Netty、Tomcat接收网络请求
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostName(), url.getPort());
    }
}
