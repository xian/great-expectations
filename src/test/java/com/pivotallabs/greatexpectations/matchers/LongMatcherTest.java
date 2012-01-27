package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.GreatExpectations;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LongMatcherTest {

  private LongMatcher fortyTwo;

  @Before
  public void setUp() throws Exception {
    fortyTwo = newExpect(new Long(42L));
  }

  @Test
  public void toEqual_isOverloadedToAlsoAcceptIntegerExpectedValues() throws Exception {
    assertTrue(fortyTwo.toEqual(42));
    assertFalse(fortyTwo.toEqual(99));
  }

  @Test
  public void toBeGreaterThan_isOverloadedToAlsoAcceptIntegerExpectedValues() throws Exception {
    assertTrue(fortyTwo.toBeGreaterThan(41));
    assertFalse(fortyTwo.toBeGreaterThan(42));
    assertFalse(fortyTwo.toBeGreaterThan(43));
  }

  @Test
  public void toBeLessThan_isOverloadedToAlsoAcceptIntegerExpectedValues() throws Exception {
    assertTrue(fortyTwo.toBeLessThan(43));
    assertFalse(fortyTwo.toBeLessThan(42));
    assertFalse(fortyTwo.toBeLessThan(41));
  }

  private LongMatcher newExpect(Long value) {
    LongMatcher longMatcher = new LongMatcher();
    GreatExpectations.setActual(longMatcher, value);
    return longMatcher;
  }
}
