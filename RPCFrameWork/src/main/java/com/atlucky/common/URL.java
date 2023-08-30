package com.atlucky.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.net.jsse.PEMFile;

/**
 * @Date 2023/8/30 16:10
 * @Author XiaoHu
 * @Description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class URL {
    private String hostName;
    private Integer port;


}
