package com.barry.util.logger.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM6;

/**
 * @author yunfan
 * @date 2023/7/11
 */
public class ClassVisitorAdapter extends ClassVisitor {
    public ClassVisitorAdapter(ClassVisitor visitor) {
        super(ASM6, visitor);
    }


    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
        return new MethodEnhanceVisitor(methodVisitor, access, name, descriptor);
    }
}
