package com.barry.logger;

import org.gradle.api.Project;
import org.gradle.api.logging.LogLevel;

/**
 * @author yunfan
 * @since 2023/7/31
 */
public class Logger {

    private static final String TAG = "[enhance_log]";
    private static Project project;

    public Logger() {
    }

    public static void attach(Project project) {
        Logger.project = project;
    }

    public static void d(String msg) {
        project.getLogger().log(LogLevel.DEBUG, TAG + msg);
    }

    public static void e(String msg) {
        project.getLogger().log(LogLevel.ERROR, TAG + msg);
    }

    public static void e(String msg, Throwable th) {
        project.getLogger().log(LogLevel.ERROR, msg, TAG + th);
    }
}
