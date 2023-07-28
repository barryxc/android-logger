package com.barry.util.core.api;

/**
 * @author yunfan
 * @date 2023/7/28
 */
public class test {


    public void test() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTraceElements[1];

        System.out.println(element.getClassName());
        System.out.println(element.getMethodName());
        System.out.println(element.getLineNumber());
        System.out.println(element.getFileName());
    }
}
