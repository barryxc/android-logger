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
