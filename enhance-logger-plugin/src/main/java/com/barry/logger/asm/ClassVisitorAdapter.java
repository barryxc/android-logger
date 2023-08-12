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

package com.barry.logger.asm;

import static org.objectweb.asm.Opcodes.ASM6;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * @author yunfan
 * @since 2023/7/11
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
