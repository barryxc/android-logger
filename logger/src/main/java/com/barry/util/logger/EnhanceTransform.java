package com.barry.util.logger;


import com.android.build.api.transform.DirectoryInput;
import com.android.build.api.transform.Format;
import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformInvocation;
import com.android.build.gradle.internal.pipeline.TransformManager;
import com.barry.util.logger.asm.ClassVisitorAdapter;


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
 * @date 2023/7/10
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
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    @Override
    public boolean isIncremental() {
        return true;
    }


    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation);
        System.out.println("isIncremental is " + transformInvocation.isIncremental());
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

                    String className = f.getAbsolutePath().substring(src.getAbsolutePath().length() + 1, f.getAbsolutePath().length() - ".class".length()).replace(File.separatorChar, '.');

                    if (className.startsWith("com.barry.util")) {
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
