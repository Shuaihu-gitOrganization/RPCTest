package com.atlucky.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @Date 2023/8/30 14:24
 * @Author XiaoHu
 * @Description
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Invocation implements Serializable {
    private String interfaceName;
    private String methodName;

    private Class[] parameterTypes;

    private Object[] parameters;

    //private String version;

}
