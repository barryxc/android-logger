package com.example.javalib;

import org.objectweb.asm.util.ASMifier;

import java.io.IOException;
import java.util.concurrent.Callable;

import picocli.CommandLine;

/**
 * @author yunfan
 * @date 2023/7/14
 */
@CommandLine.Command(name = "asmfy", description = "generate bytecode")
public class ShellCommand implements Callable {

    @CommandLine.Option(names = {"-i", "--input"}, description = "input file")
    public String path;


    public int test() throws IOException {
        try {
            ASMifier.main(new String[]{path});
            return 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer call() throws Exception {
        if (path == null || path.isEmpty()) {
            System.out.println("need input file path");
            return -1;
        }
        return test();
    }
}