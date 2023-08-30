package com.atlucky.register;

import com.atlucky.common.URL;

import java.util.*;

/**
 * @Date 2023/8/30 16:13
 * @Author XiaoHu
 * @Description
 **/
public class MapRemoteRegister {
    public static Map<String,List<URL>> map=new HashMap<String,List<URL>> ();

    public static void remoteRegister(String interfaceName,URL url){
        List<URL> urls = map.get(interfaceName);
        if (Objects.isNull(urls)){
            urls=new ArrayList<>();

        }
        urls.add(url);
        map.put(interfaceName,urls);

    }
    public static List<URL> getRemoteRegister(String interfaceName){
        return map.get(interfaceName);
    }

}
