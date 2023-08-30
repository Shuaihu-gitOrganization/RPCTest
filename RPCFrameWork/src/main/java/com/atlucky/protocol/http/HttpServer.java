package com.atlucky.protocol.http;

import com.atlucky.protocol.http.servlet.DispatcherServlet;
import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

/**
 * @Date 2023/8/30 13:50
 * @Author XiaoHu
 * @Description
 **/
public class HttpServer {
    public void start(String hostname,Integer port){
        //读取用户的配置
        Tomcat tomcat = new Tomcat();

        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");

        Connector connector = new Connector();
        connector.setPort(port);

        Engine engine = new StandardEngine();
        engine.setDefaultHost(hostname);

        Host standardHost = new StandardHost();
        standardHost.setName(hostname);

        String contextPath="";

        Context context = new StandardContext();
         context.setPath(contextPath);
         context.addLifecycleListener(new Tomcat.FixContextListener());

         standardHost.addChild(context);

         engine.addChild(standardHost);

         service.setContainer(engine);
         service.addConnector(connector);

         tomcat.addServlet(contextPath,"disPatcher",new DispatcherServlet());
         context.addServletMappingDecoded("/*","disPatcher");

        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }
}
