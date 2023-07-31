package com.example.javalib;

import picocli.CommandLine;

/**
 * @author yunfan
 * @date 2023/7/17
 */
public class ShellMain {
    public static void main(String[] args) {
        new CommandLine(new ShellCommand()).execute(args);
    }
}
