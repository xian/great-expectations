package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.GreatExpectations;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DoubleMatcherTest {

  private DoubleMatcher fortyTwo;

  @Before
  public void setUp() throws Exception {
    fortyTwo = newExpect(new Double(42));
  }

  @Test
  public void toEqual_isOverloadedToAlsoAcceptLongExpectedValues() throws Exception {
    assertTrue(fortyTwo.toEqual(42L));
    assertFalse(fortyTwo.toEqual(99L));
  }

  @Test
  public void toEqual_isOverloadedToCompareToAnotherDoubleWithinADelta() throws Exception {
    assertTrue(fortyTwo.toEqual(42.01, 0.02));
    assertFalse(fortyTwo.toEqual(42.01, 0.001));
  }

  @Test
  public void toBeGreaterThan_isOverloadedToAlsoAcceptLongAndFloatExpectedValues() throws Exception {
    assertTrue(fortyTwo.toBeGreaterThan(41L));
    assertTrue(fortyTwo.toBeGreaterThan(41f));

    assertFalse(fortyTwo.toBeGreaterThan(42L));
    assertFalse(fortyTwo.toBeGreaterThan(43L));
    assertFalse(fortyTwo.toBeGreaterThan(42f));
    assertFalse(fortyTwo.toBeGreaterThan(43f));
  }

  @Test
  public void toBeLessThan_isOverloadedToAlsoAcceptLongAndFloatExpectedValues() throws Exception {
    assertTrue(fortyTwo.toBeLessThan(43L));
    assertTrue(fortyTwo.toBeLessThan(43f));

    assertFalse(fortyTwo.toBeLessThan(42L));
    assertFalse(fortyTwo.toBeLessThan(41L));
    assertFalse(fortyTwo.toBeLessThan(42f));
    assertFalse(fortyTwo.toBeLessThan(41f));
  }

  private DoubleMatcher newExpect(Double value) {
    DoubleMatcher doubleMatcher = new DoubleMatcher();
    GreatExpectations.setActual(doubleMatcher, value);
    return doubleMatcher;
  }
}
