package com.pivotallabs.greatexpectations;

import javassist.*;

public class GreatExpectations {

    public static RuntimeException lastExpectTrace = null;
    private static final Loader LOADER;
    private static final String WRAPPER_SUFFIX = "$$wrapper";

    static {
        LOADER = new Loader();
        LOADER.delegateLoadingOf(GreatExpectations.class.getName());
        LOADER.delegateLoadingOf(BaseExpectation.class.getName());

        try {
            LOADER.addTranslator(new ClassPool(true), new MyTranslator());
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        } catch (CannotCompileException e) {
            throw new RuntimeException(e);
        }
    }

    public static void checkForUnfinishedExpect() {
        if (lastExpectTrace != null) {
            RuntimeException e = lastExpectTrace;
            lastExpectTrace = null;
            throw e;
        }
    }

    public static boolean equalsSafely(String expected, String actual) {
        if (expected == null) {
            return actual == null;
        }
        return expected.equals(actual);
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public static boolean wrap(BaseExpectation baseExpectation, String methodName, boolean result) {
        GreatExpectations.lastExpectTrace = null;

        if (!result) {
            throw new AssertionError("Failure: Expected " + baseExpectation.actual + " " + methodName + " " + result);
        }
        return true;
    }

    public static void trace(Object... o) {
        System.out.println("Trace = " + o);
    }

    public static <T, M extends BaseExpectation<T, M>> M wrapped(Class<M> expectationClass, T actual) {
        com.pivotallabs.greatexpectations.GreatExpectations.checkForUnfinishedExpect();
        GreatExpectations.lastExpectTrace = new RuntimeException("you called expect() without a matcher!");

//        LOADER.delegateLoadingOf(expectationClass.getName());
//
        try {
            System.out.println("Instrumenting " + expectationClass);
            LOADER.delegateLoadingOf(expectationClass.getName());
            expectationClass = (Class<M>) LOADER.loadClass(expectationClass.getName() + WRAPPER_SUFFIX);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            M matcher = expectationClass.newInstance();
            matcher.actual = actual;

            matcher.not = expectationClass.newInstance();
            matcher.not.inverted = true;
            matcher.not.actual = actual;

            return matcher;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static class MyTranslator implements Translator {
        private CtClass objectCtClass;
        private CtMethod traceCtMethod;

        public void start(ClassPool classPool) throws NotFoundException, CannotCompileException {
            objectCtClass = classPool.get(Object.class.getName());
            CtClass greatExpectationsCtClass = classPool.get(GreatExpectations.class.getName());
            traceCtMethod = greatExpectationsCtClass.getDeclaredMethod("trace");
        }

        public void onLoad(ClassPool classPool, String className) throws NotFoundException, CannotCompileException {
            System.out.println("className = " + className);

//            CodeConverter codeConverter = new CodeConverter();
//            for (CtMethod ctMethod : classPool.getCtClass(className).getMethods()) {
//                boolean isOnObject = ctMethod.getDeclaringClass().equals(classPool.get("java.lang.Object"));
//                if (!isOnObject && ctMethod.getReturnType().equals(CtClass.booleanType)) {
//                    System.out.println("Instrumenting " + ctMethod);
//                    codeConverter.insertAfterMethod(ctMethod, traceCtMethod);
////                                System.out.println("ctMethod = " + ctMethod);
////                                ctClass.addMethod(CtNewMethod.make(CtClass.booleanType, ctMethod.getName(),
////                                        ctMethod.getParameterTypes(), ctMethod.getExceptionTypes(),
////                                        "{ return " + CLASS_NAME + ".wrap(\"" + ctMethod.getName() + "\", super." + ctMethod.getName() + "($1)); }", ctClass));
//                }
//            }


            if (className.endsWith(WRAPPER_SUFFIX)) {
                CtClass ctParentClass = classPool.get(className.substring(0, className.length() - WRAPPER_SUFFIX.length()));

                System.out.println("ctParentClass = " + ctParentClass);

                CtClass ctClass = classPool.makeClass(className, ctParentClass);

                for (CtMethod ctMethod : ctParentClass.getMethods()) {
                    boolean isOnObject = ctMethod.getDeclaringClass().equals(objectCtClass);
                    if (!isOnObject && ctMethod.getReturnType().equals(CtClass.booleanType)) {

                        System.out.println("ctMethod = " + ctMethod);
                        ctClass.addMethod(CtNewMethod.make(CtClass.booleanType, ctMethod.getName(),
                                ctMethod.getParameterTypes(), ctMethod.getExceptionTypes(),
                                "{ return " + GreatExpectations.class.getName() + ".wrap(this, \"" + ctMethod.getName() + "\", super." + ctMethod.getName() + "($1)); }", ctClass));
                    }
                }

                System.out.println("***OUT ctClass = " + ctClass);
            }
        }

    }
}
