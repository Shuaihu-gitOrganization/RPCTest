package com.atlucky.protocol.http;

import com.atlucky.common.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Date 2023/8/30 15:19
 * @Author XiaoHu
 * @Description 客户端发送请求
 **/
public class HttpClient {
    public String send(String hostName, Integer port, Invocation invocation){
        //用户配置

        HttpURLConnection urlConnection;
        try {
            //配置
            URL url = new URL("http", hostName, port, "/");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);


            OutputStream outputStream = urlConnection.getOutputStream();
            ObjectOutputStream stream = new ObjectOutputStream(outputStream);
            stream.writeObject(invocation);

            stream.flush();
            stream.close();
            InputStream inputStream = urlConnection.getInputStream();
            String result = IOUtils.toString(inputStream);
           return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
