package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.GreatExpectations;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FloatMatcherTest {

  private FloatMatcher fortyTwo;

  @Before
  public void setUp() throws Exception {
    fortyTwo = newExpect(new Float(42));
  }

  @Test
  public void toEqual_isOverloadedToAlsoAcceptLongExpectedValues() throws Exception {
    assertTrue(fortyTwo.toEqual(42L));
    assertFalse(fortyTwo.toEqual(99L));
  }

  @Test
  public void toEqual_isOverloadedToCompareToAnotherFloatWithinADelta() throws Exception {
    assertTrue(fortyTwo.toEqual(42.01f, 0.02f));
    assertFalse(fortyTwo.toEqual(42.01f, 0.001f));
  }

  @Test
  public void toBeGreaterThan_isOverloadedToAlsoAcceptLongAndDoubleExpectedValues() throws Exception {
    assertTrue(fortyTwo.toBeGreaterThan(41L));
    assertTrue(fortyTwo.toBeGreaterThan(41d));

    assertFalse(fortyTwo.toBeGreaterThan(42L));
    assertFalse(fortyTwo.toBeGreaterThan(43L));
    assertFalse(fortyTwo.toBeGreaterThan(42d));
    assertFalse(fortyTwo.toBeGreaterThan(43d));
  }

  @Test
  public void toBeLessThan_isOverloadedToAlsoAcceptLongAndDoubleExpectedValues() throws Exception {
    assertTrue(fortyTwo.toBeLessThan(43L));
    assertTrue(fortyTwo.toBeLessThan(43d));

    assertFalse(fortyTwo.toBeLessThan(42L));
    assertFalse(fortyTwo.toBeLessThan(41L));
    assertFalse(fortyTwo.toBeLessThan(42d));
    assertFalse(fortyTwo.toBeLessThan(41d));
  }

  private FloatMatcher newExpect(Float value) {
    FloatMatcher floatMatcher = new FloatMatcher();
    GreatExpectations.setActual(floatMatcher, value);
    return floatMatcher;
  }
}
