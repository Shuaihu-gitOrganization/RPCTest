import com.atlucky.protocol.http.HttpServer;
import com.atlucky.register.LocalRegister;
import com.common.HelloService;
import service.implement.HelloServiceImpl;

/**
 * @Date 2023/8/30 13:44
 * @Author XiaoHu
 * @Description
 **/
public class Provider {
    public static void main(String[] args) {
        LocalRegister.register(HelloService.class.getName(),"1.0" ,HelloServiceImpl.class);

        //Netty、Tomcat接收网络请求
        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost",9999);
    }
}
