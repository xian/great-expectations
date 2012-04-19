package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.GreatExpectations;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SetMatcherTest {
  @Test
  public void testContain() throws Exception {
    assertTrue(newExpect(asSet("a", "b", "c")).toContain("b", "a", "c"));
    assertFalse(newExpect(asSet("a", "b", "c")).toContain("b", "a", "c", "d"));
  }

  @Test public void toContainExactly() throws Exception {
    assertFalse(newExpect(asSet("a", "b", "c")).toContainExactly("a"));
    assertFalse(newExpect(asSet("a", "b", "c")).toContainExactly("d"));

    assertFalse(newExpect(asSet("a", "b", "c")).toContainExactly("a", "b"));
    assertTrue(newExpect(asSet("a", "b", "c")).toContainExactly("a", "b", "c"));
    assertTrue(newExpect(asSet("a", "b", "c")).toContainExactly("b", "c", "a"));
    assertFalse(newExpect(asSet("a", "b", "c")).toContainExactly("a", "b", "c", "d"));
    assertFalse(newExpect(asSet("a", "b", "c")).toContainExactly("a", "c"));

    assertFalse(newExpect(asSet("a", "b", "c")).toContainExactly("a", "b", "b", "c"));
    assertTrue(newExpect(asSet(null, "b", "c")).toContainExactly(null, "b", "c"));
  }

  @Test
  public void toBeEmpty() throws Exception {
    assertTrue(newExpect(asSet()).toBeEmpty());
    assertFalse(newExpect(asSet("a", "b", "c")).toBeEmpty());
  }

  @Test
  public void toNumber() throws Exception {
    assertTrue(newExpect(asSet()).toNumber(0));
    assertTrue(newExpect(asSet("a", "b", "c")).toNumber(3));
  }

  ///////////////////

  private <T> Set<T> asSet(T... ts) {
    HashSet<T> set = new HashSet<T>();
    Collections.addAll(set, ts);
    return set;
  }

  private <T extends Set<X>, X> SetMatcher<T, X, ?> newExpect(T value) {
    SetMatcher<T, X, ?> setMatcher = new SetMatcher();
    GreatExpectations.setActual(setMatcher, value);
    return setMatcher;
  }
}
