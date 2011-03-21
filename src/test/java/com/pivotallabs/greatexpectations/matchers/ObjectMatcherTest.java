package com.pivotallabs.greatexpectations.matchers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ObjectMatcherTest {
  @Test
  public void toBe_shouldReturnTrueIffObjectsAreIdentical() throws Exception {
    String abc = "abc";
    assertTrue(newExpect(abc).toBe(abc));
    assertFalse(newExpect(abc).toBe("def"));
    assertFalse(newExpect(abc).toBe(new String(abc)));
  }

  @Test(expected = NullPointerException.class)
  public void toBe_whenActualIsNull_shouldThrowNullPointerException() throws Exception {
    newExpect(null).toBe("anything");
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

  ///////////////////

  private ObjectMatcher<Object, ?> newExpect(Object value) {
    ObjectMatcher<Object, ?> objectMatcher = new ObjectMatcher();
    objectMatcher.actual = value;
    return objectMatcher;
  }

  class A {
  }

  class B extends A {
  }

  class C {
  }
}
