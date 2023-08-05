package com.barry.logger;

import com.android.build.gradle.AppExtension;
import com.android.build.gradle.LibraryExtension;

import org.gradle.api.Project;

/**
 * @author yunfan
 * @date 2023/8/1
 */
public class Utils {

    public static boolean isAndroidLibrary(Project project) {
        return (project.getExtensions().findByType(LibraryExtension.class) != null);
    }

    public static boolean isAndroidApp(Project project) {
        return (project.getExtensions().findByType(AppExtension.class) != null);
    }
}
