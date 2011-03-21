package com.pivotallabs.greatexpectations.expectors;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IterableExpectationTest {
  @Test
  public void toContain() throws Exception {
    assertTrue(newExpect(Arrays.asList("a", "b", "c")).toContain("a"));
    assertTrue(newExpect(Arrays.asList("a", "b", "c")).toContain("b"));
    assertTrue(newExpect(Arrays.asList("a", "b", "c")).toContain("c"));
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContain("d"));

    assertTrue(newExpect(Arrays.asList("a", "b", "c")).toContain("a", "b"));
    assertTrue(newExpect(Arrays.asList("a", "b", "c")).toContain("a", "b", "c"));
    assertTrue(newExpect(Arrays.asList("a", "b", "c")).toContain("a", "c"));
    assertTrue(newExpect(Arrays.asList("a", "b", "c")).toContain("b", "c"));

    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContain("c", "b"));
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContain("c", "a"));
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContain("a", "c", "b"));
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContain("a", "a"));
  }

  @Test
  public void toBeEmpty() throws Exception {
    assertTrue(newExpect(Arrays.asList()).toBeEmpty());
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toBeEmpty());
  }

  ///////////////////

  private <T extends Iterable<X>, X> IterableExpectation<T, X, ?> newExpect(T value) {
    IterableExpectation<T, X, ?> iterableExpectation = new IterableExpectation();
    iterableExpectation.actual = value;
    return iterableExpectation;
  }

}