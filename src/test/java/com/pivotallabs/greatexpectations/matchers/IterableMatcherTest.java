package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.GreatExpectations;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IterableMatcherTest {
  @Test
  public void testContain() throws Exception {
    assertTrue(newExpect(Arrays.asList("a", "b", "c")).toContain("b", "a", "c"));
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContain("b", "a", "c", "d"));
  }
  
  @Test
  public void toContainInOrder() throws Exception {
    assertTrue(newExpect(Arrays.asList("a", "b", "c")).toContainInOrder("a"));
    assertTrue(newExpect(Arrays.asList("a", "b", "c")).toContainInOrder("b"));
    assertTrue(newExpect(Arrays.asList("a", "b", "c")).toContainInOrder("c"));
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContainInOrder("d"));

    assertTrue(newExpect(Arrays.asList("a", "b", "c")).toContainInOrder("a", "b"));
    assertTrue(newExpect(Arrays.asList("a", "b", "c")).toContainInOrder("a", "b", "c"));
    assertTrue(newExpect(Arrays.asList("a", "b", "c")).toContainInOrder("a", "c"));
    assertTrue(newExpect(Arrays.asList("a", "b", "c")).toContainInOrder("b", "c"));

    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContainInOrder("c", "b"));
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContainInOrder("c", "a"));
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContainInOrder("a", "c", "b"));
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContainInOrder("a", "a"));
  }

  @Test public void toContainExactly() throws Exception {
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContainExactly("a"));
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContainExactly("d"));

    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContainExactly("a", "b"));
    assertTrue(newExpect(Arrays.asList("a", "b", "c")).toContainExactly("a", "b", "c"));
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContainExactly("b", "c", "a"));
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContainExactly("a", "b", "c", "c"));
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContainExactly("a", "c"));

    assertTrue(newExpect(Arrays.asList("a", "b", "b", "c")).toContainExactly("a", "b", "b", "c"));
    assertFalse(newExpect(Arrays.asList("a", "b", "b", "c")).toContainExactly("a", "b", "c"));
    assertTrue(newExpect(Arrays.asList("a", "b", "b", "c")).toContainExactly("a", "b", "b", "c"));
    assertFalse(newExpect(Arrays.asList("a", "b", "b", "c")).toContainExactly("a", "b", "c", "b"));
  }

  @Test
  public void toBeEmpty() throws Exception {
    assertTrue(newExpect(Arrays.asList()).toBeEmpty());
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toBeEmpty());
  }

  ///////////////////

  private <T extends Iterable<X>, X> IterableMatcher<T, X, ?> newExpect(T value) {
    IterableMatcher<T, X, ?> iterableMatcher = new IterableMatcher();
    GreatExpectations.setActual(iterableMatcher, value);
    return iterableMatcher;
  }
}
