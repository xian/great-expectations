package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.GreatExpectations;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IntegerMatcherTest {

  private IntegerMatcher fortyTwo;

  @Before
  public void setUp() throws Exception {
    fortyTwo = newExpect(new Integer(42));
  }

  @Test
  public void toEqual_isOverloadedToAlsoAcceptLongAndDoubleExpectedValues() throws Exception {
    assertTrue(fortyTwo.toEqual(42L));
    assertTrue(fortyTwo.toEqual(42d));

    assertFalse(fortyTwo.toEqual(99L));
    assertFalse(fortyTwo.toEqual(99d));
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

  private IntegerMatcher newExpect(Integer value) {
    IntegerMatcher integerMatcher = new IntegerMatcher();
    GreatExpectations.setActual(integerMatcher, value);
    return integerMatcher;
  }
}
