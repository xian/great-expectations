package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.GreatExpectations;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateMatcherTest {
  @Test
  public void testToBeLaterThan() throws Exception {
    assertTrue(newExpect(new Date(20000)).toBeLaterThan(new Date(10000)));
    assertFalse(newExpect(new Date(20000)).toBeLaterThan(new Date(20000)));
    assertFalse(newExpect(new Date(20000)).toBeLaterThan(new Date(30000)));
  }

  @Test
  public void testToBeEarlierThan() throws Exception {
    assertFalse(newExpect(new Date(20000)).toBeEarlierThan(new Date(10000)));
    assertFalse(newExpect(new Date(20000)).toBeEarlierThan(new Date(20000)));
    assertTrue(newExpect(new Date(20000)).toBeEarlierThan(new Date(30000)));
  }

  ///////////////////

  private <T extends Date, X> DateMatcher<T, ?> newExpect(T value) {
    DateMatcher dateMatcher = new DateMatcher();
    GreatExpectations.setActual(dateMatcher, value);
    return dateMatcher;
  }
}
