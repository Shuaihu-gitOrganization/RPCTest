package com.atlucky.register;

import com.atlucky.common.URL;

import java.io.*;
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
        saveFile();

    }
    public static List<URL> getRemoteRegister(String interfaceName){
        map = getFile();
        return map.get(interfaceName);
    }


    public static void  saveFile(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/temp.txt");
            ObjectOutputStream stream = new ObjectOutputStream(fileOutputStream) ;
                stream.writeObject(map);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static Map<String,List<URL>>  getFile(){
        try {
            FileInputStream fileInputStream = new FileInputStream("/temp.txt");
            ObjectInputStream stream = new ObjectInputStream(fileInputStream) ;
            return (Map<String,List<URL>>) stream.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
