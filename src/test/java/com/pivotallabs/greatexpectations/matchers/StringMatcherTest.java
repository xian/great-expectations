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

  @Test
  public void testToContain() throws Exception {
    assertTrue(newExpect("Hello World").toContain("World"));
    assertFalse(newExpect("Hello World").toContain("Blah"));
  }

  @Test
  public void testToStartWith() throws Exception {
    assertTrue(newExpect("Hello World").toStartWith("Hello"));
    assertFalse(newExpect("Hello World").toStartWith("Blah"));
  }

  @Test
  public void testToEndWith() throws Exception {
    assertTrue(newExpect("Hello World").toEndWith("rld"));
    assertFalse(newExpect("Hello World").toEndWith("Hello"));
  }

  ///////////////////

  private StringMatcher<String, ?> newExpect(String value) {
    StringMatcher<String, ?> StringMatcher = new StringMatcher();
    GreatExpectations.setActual(StringMatcher, value);
    return StringMatcher;
  }
}
