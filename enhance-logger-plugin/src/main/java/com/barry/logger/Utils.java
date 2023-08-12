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

import com.android.build.gradle.AppExtension;
import com.android.build.gradle.LibraryExtension;

import org.gradle.api.Project;

/**
 * @author yunfan
 * @since 2023/8/1
 */
public class Utils {

    public static boolean isAndroidLibrary(Project project) {
        return (project.getExtensions().findByType(LibraryExtension.class) != null);
    }

    public static boolean isAndroidApp(Project project) {
        return (project.getExtensions().findByType(AppExtension.class) != null);
    }
}
