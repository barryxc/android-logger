package com.barry.util.logger.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author yunfan
 * @date 2023/7/11
 */
public class EnhanceFileUtil {


    public static Collection<File> listClassFiles(File directorFile) {

        List<File> files = new ArrayList<>();
        recur(directorFile, files, ".class");
        return files;
    }

    private static void recur(File directorFile, List<File> files, String suffix) {

        if (directorFile != null && directorFile.isDirectory()) {
            File[] listFiles = directorFile.listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    if (!file.isDirectory() && (file.getName().endsWith(suffix) || file.getName().endsWith(suffix.toUpperCase()))) {
                        files.add(file);
                    } else {
                        recur(file, files, suffix);
                    }
                }
            }
        }
    }
}
