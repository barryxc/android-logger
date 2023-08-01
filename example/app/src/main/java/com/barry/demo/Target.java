package com.barry.demo;

import com.barry.logger.api.MethodCost;
import com.barry.logger.api.MethodInspect;

/**
 * @author yunfan
 * @date 2023/8/1
 */
public class Target {


    public static void main(String[] args) throws InterruptedException {

    }

    @MethodCost
    public void cost() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @MethodInspect
    public void test() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
