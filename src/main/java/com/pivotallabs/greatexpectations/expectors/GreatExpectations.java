package com.pivotallabs.greatexpectations.expectors;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Loader;
import javassist.NotFoundException;
import javassist.Translator;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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
  public static boolean wrap(BaseExpectation baseExpectation, String methodName, boolean result, Object[] expectArgs) {
    GreatExpectations.lastExpectTrace = null;

    if (!result) {
      StringBuilder message = new StringBuilder();
      message
          .append("Failure: Expected ")
          .append(baseExpectation.actual)
          .append(" ")
          .append(methodName);

      for (int i = 0, expectArgsLength = expectArgs.length; i < expectArgsLength; i++) {
        Object expectArg = expectArgs[i];
        message.append(i == 0 ? " " : ", ");
        message.append(expectArg);
      }
      throw new AssertionError(message.toString());
    }
    return true;
  }

  public static void trace(Object... o) {
    System.out.println("Trace = " + o);
  }

  public static <T, M extends BaseExpectation<T, M>> M wrapped(Class<M> expectationClass, T actual) {
    GreatExpectations.checkForUnfinishedExpect();
    GreatExpectations.lastExpectTrace = new RuntimeException("you called expect() without a matcher!");

//        LOADER.delegateLoadingOf(expectationClass.getName());
//
    try {
      System.out.println("Instrumenting " + expectationClass);
      LOADER.delegateLoadingOf(expectationClass.getName());
      expectationClass = (Class<M>) LOADER.loadClass(expectationClass.getName() + WRAPPER_SUFFIX);
    } catch (ClassNotFoundException e) {
//      e.printStackTrace();
      throw new RuntimeException(e);
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

  static class MyTranslator implements Translator {
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

        System.out.println("ctParentClass = " + ctParentClass.getName());

        CtClass ctClass = classPool.makeClass(className, ctParentClass);

        for (CtMethod ctMethod : ctParentClass.getMethods()) {
          boolean isOnObject = ctMethod.getDeclaringClass().equals(objectCtClass);
          if (!isOnObject && ctMethod.getReturnType().equals(CtClass.booleanType)) {
            String methodBody = wrapMethodBody(ctMethod);
            CtMethod method = CtNewMethod.make(CtClass.booleanType, ctMethod.getName(),
                ctMethod.getParameterTypes(), ctMethod.getExceptionTypes(),
                methodBody, ctClass);
            ctClass.addMethod(method);
          }
        }

        System.out.println("***OUT ctClass = " + ctClass);
        try {
          ctClass.toBytecode(new DataOutputStream(new FileOutputStream("/tmp/" + ctClass.getName())));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    }

    String wrapMethodBody(CtMethod ctMethod) throws NotFoundException {

      StringBuilder methodBody = new StringBuilder();
      methodBody
          .append("{\n")
          .append("  return ")
          .append(GreatExpectations.class.getName())
          .append(".wrap(this, \"")
          .append(ctMethod.getName())
          .append("\", super.")
          .append(ctMethod.getName())
          .append("(");

      int paramCount = ctMethod.getParameterTypes().length;
      for (int i = 0; i < paramCount; i++) {
        if (i > 0) methodBody.append(", ");
        methodBody.append("$").append(i + 1);
      }

      methodBody
          .append("), new Object[] {");

      for (int i = 0; i < paramCount; i++) {
        if (i > 0) methodBody.append(", ");
        methodBody.append("$").append(i + 1);
      }

      methodBody
          .append("});\n")
          .append("}");

      System.out.println("declaring " + ctMethod.getReturnType().getName() + " "
          + ctMethod.getName() + " " + ctMethod.getSignature() + " " + methodBody.toString());
      return methodBody.toString();
    }

  }
}
