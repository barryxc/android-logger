package com.barry.lib;

import com.barry.logger.api.MethodCost;
import com.barry.logger.api.MethodInspect;

import java.util.Arrays;

/**
 * @author yunfan
 * @since 2023/8/1
 */
public class Target {

    @MethodCost
    @MethodInspect
    public void test() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        System.out.println(Arrays.toString(stackTrace));
    }
}
