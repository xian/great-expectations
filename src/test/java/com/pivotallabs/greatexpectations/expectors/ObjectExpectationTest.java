package com.pivotallabs.greatexpectations.expectors;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ObjectExpectationTest {
  private ObjectExpectation<Object, ?> objectExpectation;

  @Before
  public void setUp() throws Exception {
    objectExpectation = new ObjectExpectation();
  }

  @Test
  public void toBe_shouldReturnTrueIffObjectsAreIdentical() throws Exception {
    objectExpectation.actual = "abc";
    assertEquals(true, objectExpectation.toBe(objectExpectation.actual));
    assertEquals(false, objectExpectation.toBe("def"));
    assertEquals(false, objectExpectation.toBe(new String("abc")));
  }

  @Test(expected = NullPointerException.class)
  public void toBe_whenActualIsNull_shouldThrowNullPointerException() throws Exception {
    objectExpectation.actual = null;
    objectExpectation.toEqual("anything");
  }

  @Test
  public void toEqual_shouldReturnTrueIffObjectsAreEqual() throws Exception {
    objectExpectation.actual = "abc";
    assertEquals(true, objectExpectation.toEqual("abc"));
    assertEquals(false, objectExpectation.toEqual("def"));
  }

  @Test(expected = NullPointerException.class)
  public void toEqual_whenActualIsNull_shouldThrowNullPointerException() throws Exception {
    objectExpectation.actual = null;
    objectExpectation.toEqual("anything");
  }

  @Test(expected = NullPointerException.class)
  public void toEqual_whenActualAndExpectedAreNull_shouldThrowNullPointerException() throws Exception {
    objectExpectation.actual = null;
    objectExpectation.toEqual(null);
  }
}
