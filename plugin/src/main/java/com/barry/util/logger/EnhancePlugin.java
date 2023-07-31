package com.barry.util.logger;


import com.android.build.api.variant.VariantFilter;
import com.android.build.gradle.AppExtension;
import com.android.build.gradle.BaseExtension;
import com.android.build.gradle.LibraryExtension;
import com.android.build.gradle.internal.VariantManager;
import com.android.build.gradle.internal.dsl.BuildType;

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
                    BaseExtension androidExtension = (BaseExtension) project.getExtensions().findByType(BaseExtension.class);

                    if (androidExtension != null) {
                        androidExtension.variantFilter(new Action<VariantFilter>() {
                            @Override
                            public void execute(VariantFilter variantFilter) {

                                if (variantFilter.getName().contains("debug")) {
                                    androidExtension.registerTransform(new EnhanceTransform(project));
                                }
                            }
                        });
                    }

                }
            }
        });
    }
}
