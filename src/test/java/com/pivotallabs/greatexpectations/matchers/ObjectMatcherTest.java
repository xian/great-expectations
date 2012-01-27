package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.GreatExpectations;
import org.junit.Test;

import static com.pivotallabs.greatexpectations.GreatExpectations.wrapped;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ObjectMatcherTest {
  @Test
  public void toBeSameInstance_shouldReturnTrueIffObjectsAreIdentical() throws Exception {
    String abc = "abc";
    assertTrue(newExpect(abc).toBeSameInstance(abc));
    assertFalse(newExpect(abc).toBeSameInstance("def"));
    assertFalse(newExpect(abc).toBeSameInstance(new String(abc)));
  }

  @Test
  public void toBeSameInstance_shouldNotExplicitlyCheckForNull() throws Exception {
    newExpect(null).toBeSameInstance("anything");
  }

  @Test(expected = NullPointerException.class)
  public void toBeSameInstance_whenWrappedAndActualIsNull_shouldThrowNullPointerException() throws Exception {
    wrapped(ObjectMatcher.class, null).toBeSameInstance("anything");
  }

  @Test
  public void toEqual_shouldReturnTrueIffObjectsAreEqual() throws Exception {
    assertTrue(newExpect("abc").toEqual("abc"));
    assertEquals(false, newExpect("abc").toEqual("def"));
  }

  @Test(expected = NullPointerException.class)
  public void toEqual_whenActualIsNull_shouldThrowNullPointerException() throws Exception {
    newExpect(null).toEqual("anything");
  }

  @Test(expected = NullPointerException.class)
  public void toEqual_whenActualAndExpectedAreNull_shouldThrowNullPointerException() throws Exception {
    newExpect(null).toEqual(null);
  }

  @Test
  public void toBeInstanceOf() throws Exception {
    assertTrue(newExpect(new B()).toBeInstanceOf(A.class));
    assertTrue(newExpect(new B()).toBeInstanceOf(B.class));
    assertEquals(false, newExpect(new B()).toBeInstanceOf(C.class));

    assertTrue(newExpect(new A()).toBeInstanceOf(A.class));
    assertFalse(newExpect(new A()).toBeInstanceOf(B.class));
  }

  @Test
  public void toBeNull() throws Exception {
    assertFalse(newExpect("abc").toBeNull());
    assertTrue(newExpect(null).toBeNull());
  }

  @Test
  public void wrapped_toBeNull() throws Exception {
    ((ObjectMatcher) wrapped(ObjectMatcher.class, "abc").not).toBeNull();
    wrapped(ObjectMatcher.class, null).toBeNull();
  }

  ///////////////////

  private ObjectMatcher<Object, ?> newExpect(Object value) {
    ObjectMatcher<Object, ?> objectMatcher = new ObjectMatcher();
    GreatExpectations.setActual(objectMatcher, value);
    return objectMatcher;
  }

  class A {
  }

  class B extends A {
  }

  class C {
  }
}
