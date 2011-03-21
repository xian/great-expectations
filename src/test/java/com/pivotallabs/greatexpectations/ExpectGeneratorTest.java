package com.pivotallabs.greatexpectations;

import com.pivotallabs.greatexpectations.matchers.BooleanMatcher;
import com.pivotallabs.greatexpectations.matchers.ComparableMatcher;
import com.pivotallabs.greatexpectations.matchers.IterableMatcher;
import com.pivotallabs.greatexpectations.matchers.ObjectMatcher;
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
    assertEquals("    public static <T extends Object, M extends ObjectMatcher<T, M>> ObjectMatcher<T, M> expect(T actual) {\n" +
        "        return wrapped(ObjectMatcher.class, actual);\n" +
        "    }",
        expectGenerator.generateFor(ObjectMatcher.class));
  }

  @Test
  public void forSubclass_shouldGenerateExpectLines() throws Exception {
    assertEquals("    public static <T extends Boolean, M extends BooleanMatcher<T, M>> BooleanMatcher<T, M> expect(T actual) {\n" +
        "        return wrapped(BooleanMatcher.class, actual);\n" +
        "    }",
        expectGenerator.generateFor(BooleanMatcher.class));
  }

  @Test
  public void forGeneric_shouldGenerateExpectLines() throws Exception {
    assertEquals("    public static <T extends Comparable, M extends ComparableMatcher<T, M>> ComparableMatcher<T, M> expect(T actual) {\n" +
        "        return wrapped(ComparableMatcher.class, actual);\n" +
        "    }",
        expectGenerator.generateFor(ComparableMatcher.class));
  }

  @Test
  public void forGeneric_shouldGenerateDirectObjectGeneric() throws Exception {
    assertEquals("    public static <T extends Iterable<X>, X, M extends IterableMatcher<T, X, M>> IterableMatcher<T, X, M> expect(T actual) {\n" +
        "        return wrapped(IterableMatcher.class, actual);\n" +
        "    }",
        expectGenerator.generateFor(IterableMatcher.class));
  }
}
