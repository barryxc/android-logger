package com.barry.logger;


import com.android.build.gradle.BaseExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.ProjectConfigurationException;

/**
 * @author yunfan
 * @date 2023/7/10
 */
public class EnhancePlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        Logger.attach(project);
        createExtensions(project);
        checkAndroidPlugins(project);
        registerTransform(project);
    }

    private void createExtensions(Project project) {
        project.getExtensions().create("logger", LoggerExtension.class);
    }

    private void registerTransform(Project project) {
        BaseExtension android = (BaseExtension) project.getExtensions().findByType(BaseExtension.class);
        if (android != null) {
            android.registerTransform(new EnhanceTransform(project));
        }
    }

    private void checkAndroidPlugins(Project project) {
        if (!project.getPlugins().hasPlugin("com.android.library")
                && !project.getPlugins().hasPlugin("com.android.application")) {
            throw new ProjectConfigurationException("enhance-log-plugin must be applied in project that has android plugin!", new Throwable(""));
        }
    }
}
