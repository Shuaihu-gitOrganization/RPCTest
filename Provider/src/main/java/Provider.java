import com.atlucky.common.URL;
import com.atlucky.protocol.http.HttpServer;
import com.atlucky.register.LocalRegister;
import com.atlucky.register.MapRemoteRegister;
import com.common.HelloService;
import service.implement.HelloServiceImpl;

import java.util.List;

/**
 * @Date 2023/8/30 13:44
 * @Author XiaoHu
 * @Description
 **/
public class Provider {
    public static void main(String[] args) {
        //本地注册
        LocalRegister.register(HelloService.class.getName(),"1.0" ,HelloServiceImpl.class);


        //注册中心   服务注册
        URL url = new URL("localhost", 9999);
        MapRemoteRegister.remoteRegister(HelloService.class.getName(),url);

        //Netty、Tomcat接收网络请求
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostName(), url.getPort());
    }
}
