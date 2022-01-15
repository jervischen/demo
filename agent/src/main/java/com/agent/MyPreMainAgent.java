package com.agent;

import java.lang.instrument.Instrumentation;

/**
 * @author Chen Xiao
 * @since 2021-09-13 16:24
 */
public class MyPreMainAgent {

    //方法名和参数都是固定的 premain表示在主程序运行之前运行
    public static void premain(String agentArgs, Instrumentation inst) {

    }

    public static void agentmain(String agentArgs, Instrumentation inst) throws Exception {


    }
}
