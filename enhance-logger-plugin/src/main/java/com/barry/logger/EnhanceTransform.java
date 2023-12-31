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


import com.android.build.api.transform.DirectoryInput;
import com.android.build.api.transform.Format;
import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformInvocation;
import com.android.build.gradle.internal.pipeline.TransformManager;
import com.barry.logger.asm.ClassVisitorAdapter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.gradle.api.Project;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

/**
 * @author yunfan
 * @since 2023/7/10
 */
public class EnhanceTransform extends Transform {
    private final Project mProject;

    public EnhanceTransform(Project project) {
        mProject = project;
    }

    @Override
    public String getName() {
        return "enhance_logger";
    }

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        if (Utils.isAndroidApp(mProject)) {
            return TransformManager.SCOPE_FULL_PROJECT;
        }

        return TransformManager.PROJECT_ONLY;
    }

    @Override
    public boolean isIncremental() {
        return true;
    }


    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation);


        Collection<TransformInput> inputs = transformInvocation.getInputs();
        for (TransformInput input : inputs) {
            for (DirectoryInput directoryInput : input.getDirectoryInputs()) {

                File src = directoryInput.getFile();
                File dst = transformInvocation.getOutputProvider().getContentLocation(
                        directoryInput.getName(), directoryInput.getContentTypes(),
                        directoryInput.getScopes(), Format.DIRECTORY);

                FileUtils.forceMkdir(dst);

                Collection<File> files = FileUtils.listFiles(src, new SuffixFileFilter(".class"), TrueFileFilter.INSTANCE);
                for (File f : files) {
                    try {
                        FileInputStream fis = new FileInputStream(f.getAbsoluteFile());
                        //具体的插桩逻辑
                        byte[] byteCode = referHackWhenInit(fis);
                        fis.close();

                        FileOutputStream fos = new FileOutputStream(f.getAbsoluteFile());
                        fos.write(byteCode);
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                FileUtils.copyDirectory(src, dst);
            }
        }
    }

    private byte[] referHackWhenInit(FileInputStream fis) throws IOException {
        ClassReader classReader = new ClassReader(fis);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassVisitorAdapter classVisitorAdapter = new ClassVisitorAdapter(classWriter);
        classReader.accept(classVisitorAdapter, ClassReader.SKIP_FRAMES);
        return classWriter.toByteArray();
    }

}
