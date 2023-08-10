/*
 * Copyright (c) barry 2023 - 2023, All Rights Reserved.
 */

package com.barry.asmfy;

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
