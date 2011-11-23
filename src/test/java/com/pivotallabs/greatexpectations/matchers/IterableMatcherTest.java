package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.GreatExpectations;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
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
    assertTrue(newExpect(Arrays.asList("a", null, "c")).toContainInOrder((String) null));
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContainInOrder("d"));
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContainInOrder((String) null));

    assertTrue(newExpect(Arrays.asList("a", "b", "c")).toContainInOrder("a", "b"));
    assertTrue(newExpect(Arrays.asList("a", "b", "c")).toContainInOrder("a", "b", "c"));
    assertTrue(newExpect(Arrays.asList("a", "b", "c")).toContainInOrder("a", "c"));
    assertTrue(newExpect(Arrays.asList("a", "b", "c")).toContainInOrder("b", "c"));

    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContainInOrder("c", "b"));
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContainInOrder("c", "a"));
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContainInOrder("a", "c", "b"));
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContainInOrder("a", "a"));
    assertFalse(newExpect(Arrays.asList("b", null, "c")).toContainInOrder("a"));
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
    assertFalse(newExpect(Arrays.asList(null, "b", "b", "c")).toContainExactly(null, "b", "c", "b"));
  }

  @Test
  public void toBeEmpty() throws Exception {
    assertTrue(newExpect(Arrays.asList()).toBeEmpty());
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toBeEmpty());
  }

  @Test
  public void toContainExactlyInAnyOrder() throws Exception {
    assertTrue(newExpect(Arrays.asList("a", "b", "c")).toContainExactlyInAnyOrder("a", "b", "c"));
    assertTrue(newExpect(Arrays.asList("a", "b", "c")).toContainExactlyInAnyOrder("c", "a", "b"));
    assertTrue(newExpect(Arrays.asList("a", "b", null)).toContainExactlyInAnyOrder(null, "a", "b"));
    assertTrue(newExpect(Arrays.asList("a", "a", "b")).toContainExactlyInAnyOrder("a", "b", "a"));
    assertTrue(newExpect(Arrays.asList(new String[] {null})).toContainExactlyInAnyOrder(new String[] {null}));

    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContainExactlyInAnyOrder("a"));
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContainExactlyInAnyOrder("a", "b", "d"));
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContainExactlyInAnyOrder("a", "b", "c", "d"));
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContainExactlyInAnyOrder("a", null, "d"));
    assertFalse(newExpect(Arrays.asList("a", "b", "c")).toContainExactlyInAnyOrder(new String[] {null}));
    assertFalse(newExpect(Arrays.asList("a", "b", null)).toContainExactlyInAnyOrder("a", "b", "c"));
    assertFalse(newExpect(Arrays.asList("a", "a", "b")).toContainExactlyInAnyOrder("a", "b"));
    assertFalse(newExpect(Arrays.asList("a")).toContainExactlyInAnyOrder("a", "a"));
  }

  @Test
  public void objectCounter_utilityClass() throws Exception {
    IterableMatcher.ObjectCounter<String> counter = new IterableMatcher.ObjectCounter<String>();
    assertTrue(counter.allCountsAreZero());

    assertEquals(0, counter.getCount("foo"));
    counter.decrementCount("foo");
    assertEquals(0, counter.getCount("foo"));
    assertTrue(counter.allCountsAreZero());

    counter.incrementCount("foo");
    assertEquals(1, counter.getCount("foo"));
    assertEquals(0, counter.getCount("bar"));
    assertFalse(counter.allCountsAreZero());

    counter.incrementCount("foo");
    assertEquals(2, counter.getCount("foo"));

    counter.incrementCount("bar");
    assertEquals(2, counter.getCount("foo"));
    assertEquals(1, counter.getCount("bar"));

    counter.decrementCount("foo");
    assertEquals(1, counter.getCount("foo"));
    assertEquals(1, counter.getCount("bar"));

    counter.decrementCount("foo");
    assertEquals(0, counter.getCount("foo"));

    counter.decrementCount("bar");
    assertEquals(0, counter.getCount("bar"));

    assertTrue(counter.allCountsAreZero());
  }

  ///////////////////

  private <T extends Iterable<X>, X> IterableMatcher<T, X, ?> newExpect(T value) {
    IterableMatcher<T, X, ?> iterableMatcher = new IterableMatcher();
    GreatExpectations.setActual(iterableMatcher, value);
    return iterableMatcher;
  }
}
