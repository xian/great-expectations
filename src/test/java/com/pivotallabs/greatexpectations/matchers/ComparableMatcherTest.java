package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.GreatExpectations;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ComparableMatcherTest {
  @Test
  public void testToBeGreaterThan() throws Exception {
    assertTrue(newExpect(1).toBeGreaterThan(0));
    assertTrue(newExpect(-1).toBeGreaterThan(-10));
    assertTrue(newExpect("abd").toBeGreaterThan("abc"));

    assertFalse(newExpect(-1).toBeGreaterThan(0));
    assertFalse(newExpect("abc").toBeGreaterThan("abd"));

    assertFalse(newExpect(1).toBeGreaterThan(1));
    assertFalse(newExpect("abc").toBeGreaterThan("abc"));
  }
  
  @Test
  public void testToBeLessThan() throws Exception {
    assertTrue(newExpect(0).toBeLessThan(1));
    assertTrue(newExpect(-10).toBeLessThan(-1));
    assertTrue(newExpect("abc").toBeLessThan("abd"));

    assertFalse(newExpect(0).toBeLessThan(-1));
    assertFalse(newExpect("abd").toBeLessThan("abc"));

    assertFalse(newExpect(1).toBeLessThan(1));
    assertFalse(newExpect("abc").toBeLessThan("abc"));
  }

  ///////////////////

  private <T extends Comparable> ComparableMatcher<T, ?> newExpect(T value) {
    ComparableMatcher<T, ?> comparableMatcher = new ComparableMatcher();
    GreatExpectations.setActual(comparableMatcher, value);
    return comparableMatcher;
  }

}
