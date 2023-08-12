/*
 * Copyright barry 2023
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.barry.asmfy;

import org.objectweb.asm.util.ASMifier;

import java.io.IOException;
import java.util.concurrent.Callable;

import picocli.CommandLine;

/**
 * @author yunfan
 * @since 2023/7/14
 */
@CommandLine.Command(name = "asmfy", description = "generate bytecode")
public class ShellCommand implements Callable {

    @CommandLine.Option(names = {"-i", "--input"}, description = "input .class file")
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