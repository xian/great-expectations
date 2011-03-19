package com.pivotallabs.greatexpectations;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExpectGeneratorTest {
  private ExpectGenerator expectGenerator;

  @Before
  public void setUp() throws Exception {
    expectGenerator = new ExpectGenerator("com.pivotallabs.greatexpectations");
  }

  @Test
  public void forObject_shouldGenerateExpectLines() throws Exception {
    assertEquals("    public static <T, M extends ObjectExpectation<T, M>> ObjectExpectation<T, M> expect(T actual) {\n" +
        "        return wrapped(ObjectExpectation.class, actual);\n" +
        "    }",
        expectGenerator.generateFor(ObjectExpectation.class));
  }

  @Test
  public void forSubclass_shouldGenerateExpectLines() throws Exception {
    assertEquals("    public static <T extends Boolean, M extends BooleanExpectation<T, M>> BooleanExpectation<T, M> expect(T actual) {\n" +
        "        return wrapped(BooleanExpectation.class, actual);\n" +
        "    }",
        expectGenerator.generateFor(BooleanExpectation.class));
  }

  @Test
  public void forGeneric_shouldGenerateExpectLines() throws Exception {
    assertEquals("    public static <T extends Comparable<T, M extends BooleanExpectation<T, M>> BooleanExpectation<T, M> expect(T actual) {\n" +
        "        return wrapped(BooleanExpectation.class, actual);\n" +
        "    }",
        expectGenerator.generateFor(ComparableExpectation.class));
  }
}
