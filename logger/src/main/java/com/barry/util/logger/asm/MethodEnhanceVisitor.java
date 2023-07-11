package com.barry.util.logger.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * @author yunfan
 * @date 2023/7/11
 */
public class MethodEnhanceVisitor extends AdviceAdapter {

    private int index;

    public MethodEnhanceVisitor(MethodVisitor methodVisitor, int access, String name, String descriptor) {
        super(ASM6, methodVisitor, access, name, descriptor);
    }

    @Override
    protected void onMethodEnter() {
        super.onMethodEnter();
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        index = newLocal(Type.LONG_TYPE);
        mv.visitVarInsn(LSTORE, index);

    }

    @Override
    protected void onMethodExit(int opcode) {
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        mv.visitVarInsn(LLOAD, index);
        mv.visitInsn(LSUB);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
        super.onMethodExit(opcode);

    }
}
