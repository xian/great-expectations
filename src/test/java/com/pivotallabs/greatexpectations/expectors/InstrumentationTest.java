package com.pivotallabs.greatexpectations.expectors;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InstrumentationTest {
  private ClassPool classPool;
  private CtClass objCtClass;
  private GreatExpectations.MyTranslator myTranslator;

  @Before public void setUp() throws Exception {
    classPool = ClassPool.getDefault();
    objCtClass = classPool.get(Object.class.getName());
    myTranslator = new GreatExpectations.MyTranslator();
  }

  @Test
  public void shouldWrapMethods() throws Exception {
    assertEquals("{\n" +
        "  return com.pivotallabs.greatexpectations.expectors.GreatExpectations.wrap(this, \"theMethodName\", super.theMethodName($1));\n" +
        "}",
        myTranslator.wrapMethodBody(new CtMethod(CtClass.booleanType, "theMethodName",
            new CtClass[]{CtClass.floatType}, objCtClass)));
  }

  @Test
  public void shouldPassAlongArgumentsToWrappedMethods() throws Exception {
    assertEquals("{\n" +
        "  return com.pivotallabs.greatexpectations.expectors.GreatExpectations.wrap(this, \"theMethodName\", super.theMethodName());\n" +
        "}",
        myTranslator.wrapMethodBody(new CtMethod(CtClass.booleanType, "theMethodName",
            new CtClass[]{}, objCtClass)));

    assertEquals("{\n" +
        "  return com.pivotallabs.greatexpectations.expectors.GreatExpectations.wrap(this, \"theMethodName\", super.theMethodName($1, $2));\n" +
        "}",
        myTranslator.wrapMethodBody(new CtMethod(CtClass.booleanType, "theMethodName",
            new CtClass[]{objCtClass, objCtClass}, objCtClass)));
  }
}
