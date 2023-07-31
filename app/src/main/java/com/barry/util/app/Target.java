package com.barry.util.app;


import com.barry.util.core.api.MethodCost;
import com.barry.util.core.api.MethodInspect;

/**
 * @author yunfan
 * @date 2023/7/11
 */
public class Target {

    public static void main(String[] args) {
        new Target().test();
    }

    @MethodCost
    public void test() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @MethodInspect
    public void test2() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
