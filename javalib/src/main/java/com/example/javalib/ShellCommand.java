package com.example.javalib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;


import picocli.CommandLine;

/**
 * @author yunfan
 * @date 2023/7/14
 */
@CommandLine.Command(name = "ticker", description = "calc file lines")
public class ShellCommand implements Callable {

    public boolean help;

    @CommandLine.Option(names = {"-i", "--input"}, description = "input file")
    public String path;


    public int test() throws IOException {
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int counter = 0;
        while (reader.readLine() != null) {
            counter++;
        }
        System.out.println("line:" + counter);
        return counter;
    }

    @Override
    public Integer call() throws Exception {
        return test();
    }
}