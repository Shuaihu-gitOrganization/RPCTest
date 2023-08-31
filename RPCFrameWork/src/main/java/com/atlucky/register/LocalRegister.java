package com.atlucky.register;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2023/8/30 14:53
 * @Author XiaoHu
 * @Description 本地注册
 **/
public class LocalRegister {
    public static Map<String,Class> map=new HashMap<String,Class>();

    public static void register(String interfaceName,String version,Class implClass){
        map.put(interfaceName+version,implClass);

    }
    public static Class getRegister(String interfaceName,String version){
        return map.get(interfaceName+version);
    }


}
