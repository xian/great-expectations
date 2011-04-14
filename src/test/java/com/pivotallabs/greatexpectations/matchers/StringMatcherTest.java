package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.GreatExpectations;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringMatcherTest {
  @Test
  public void testToMatch() throws Exception {
    assertTrue(newExpect("abc").toMatch("b"));
    assertFalse(newExpect("abc").toMatch("^b$"));
    assertFalse(newExpect("abc").toMatch("d"));
    assertTrue(newExpect("abc").toMatch("[a][b][c]"));
  }

  ///////////////////

  private StringMatcher<String, ?> newExpect(String value) {
    StringMatcher<String, ?> StringMatcher = new StringMatcher();
    GreatExpectations.setActual(StringMatcher, value);
    return StringMatcher;
  }
}
