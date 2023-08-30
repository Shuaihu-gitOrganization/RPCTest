package com.atlucky.loadbalance;

import com.atlucky.common.URL;

import java.util.List;
import java.util.Random;

/**
 * 负载平衡
 *
 * @Date 2023/8/30 16:41
 * @Author XiaoHu
 * @Description
 **/
public class LoadBalance {
    public static URL getRandom(List<URL> urls){

        Random random = new Random();
        int randomCode = random.nextInt(urls.size());
        return urls.get(randomCode);

    }
}
