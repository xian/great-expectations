package com.pivotallabs.greatexpectations;

import com.pivotallabs.greatexpectations.matchers.*;
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
    assertEquals("    public static <T extends Object, M extends ObjectMatcher<T, M>> ObjectMatcher<T, ?> expect(T actual) {\n" +
        "        return wrapped(ObjectMatcher.class, actual);\n" +
        "    }",
        expectGenerator.generateFor(ObjectMatcher.class));
  }

  @Test
  public void forBoolean_shouldGeneratePrimitiveBooleanToWorkAroundJavaBug() throws Exception {
    // cannot find symbol method valueOf(boolean) for expect(true)
    assertEquals("    public static BooleanMatcher<Boolean, ?> expect(boolean actual) {\n" +
        "        return wrapped(BooleanMatcher.class, actual);\n" +
        "    }\n" +
        "    public static <T extends Boolean, M extends BooleanMatcher<T, M>> BooleanMatcher<T, ?> expect(T actual) {\n" +
        "        return wrapped(BooleanMatcher.class, actual);\n" +
        "    }",
        expectGenerator.generateFor(BooleanMatcher.class));
  }

  @Test
  public void forSubclass_shouldGenerateExpectLines() throws Exception {
    assertEquals("    public static <T extends String, M extends StringMatcher<T, M>> StringMatcher<T, ?> expect(T actual) {\n" +
        "        return wrapped(StringMatcher.class, actual);\n" +
        "    }",
        expectGenerator.generateFor(StringMatcher.class));
  }

  @Test
  public void forGeneric_shouldGenerateExpectLines() throws Exception {
    assertEquals("    public static <T extends Comparable, M extends ComparableMatcher<T, M>> ComparableMatcher<T, ?> expect(T actual) {\n" +
        "        return wrapped(ComparableMatcher.class, actual);\n" +
        "    }",
        expectGenerator.generateFor(ComparableMatcher.class));
  }

  @Test
  public void forGeneric_shouldGenerateDirectObjectGeneric() throws Exception {
    assertEquals("    public static <T extends Iterable<X>, X, M extends IterableMatcher<T, X, M>> IterableMatcher<T, X, ?> expect(T actual) {\n" +
        "        return wrapped(IterableMatcher.class, actual);\n" +
        "    }",
        expectGenerator.generateFor(IterableMatcher.class));
  }

  @Test
  public void forMatcherOfPrimitiveWrapper_shouldGenerateTwoExpectMethods_toWorkaroundAutoboxingInsanity() throws Exception {
    assertEquals("    public static <T extends Double, M extends DoubleMatcher<T, M>> DoubleMatcher<T, ?> expect(T actual) {\n" +
        "        return wrapped(DoubleMatcher.class, actual);\n" +
        "    }\n" +
        "    public static DoubleMatcher<Double, ?> expect(double actual) {\n" +
        "        return wrapped(DoubleMatcher.class, actual);\n" +
        "    }",
        expectGenerator.generateFor(DoubleMatcher.class));
  }

  @Test
  public void matcherClasses_shouldReturnAMutableList() throws Exception {
    expectGenerator.matcherClasses().add(GreatExpectationsTest.TestMatcher.class);
  }
}
