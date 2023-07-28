package com.barry.util.logger;


import com.android.build.gradle.AppExtension;
import com.android.build.gradle.LibraryExtension;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * @author yunfan
 * @date 2023/7/10
 */
public class EnhancePlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {

        LoggerExtension logger = project.getExtensions().create("logger", LoggerExtension.class);
        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(Project project) {
                if (logger.enable) {
                    AppExtension appExtension = project.getExtensions().findByType(AppExtension.class);
                    if (appExtension != null) {
                        appExtension.registerTransform(new EnhanceTransform(project));
                    } else {
                        LibraryExtension libraryExtension = project.getExtensions().findByType(LibraryExtension.class);
                        if (libraryExtension != null) {
                            libraryExtension.registerTransform(new EnhanceTransform(project));
                        }
                    }
                }
            }
        });
    }
}
