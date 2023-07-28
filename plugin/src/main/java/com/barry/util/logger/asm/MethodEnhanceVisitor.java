package com.barry.util.logger.asm;


import org.objectweb.asm.AnnotationVisitor;
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
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        System.out.println("visitAnnotation:" + descriptor + visible);


        AnnotationVisitor annotationVisitor = super.visitAnnotation(descriptor, visible);
        return new AnnotationVisitor(ASM6, annotationVisitor) {

            @Override
            public void visit(String name, Object value) {
                super.visit(name, value);
            }

            @Override
            public void visitEnum(String name, String descriptor, String value) {
                super.visitEnum(name, descriptor, value);
            }

            @Override
            public AnnotationVisitor visitAnnotation(String name, String descriptor) {
                return super.visitAnnotation(name, descriptor);
            }

            @Override
            public AnnotationVisitor visitArray(String name) {
                return super.visitArray(name);
            }

            @Override
            public void visitEnd() {
                super.visitEnd();
            }
        };
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
