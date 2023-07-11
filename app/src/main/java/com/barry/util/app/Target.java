package com.barry.util.app;

/**
 * @author yunfan
 * @date 2023/7/11
 */
public class Target {

    public static void main(String[] args) {
        new Target().test();
    }

    public void test() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
