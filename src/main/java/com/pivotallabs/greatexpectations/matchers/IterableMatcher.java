package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.MatcherOf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@MatcherOf(value = Iterable.class, directObject = true)
public class IterableMatcher<T extends Iterable<X>, X, M extends IterableMatcher<T, X, M>> extends ObjectMatcher<T, M> {
  public boolean toContain(X... expectedItems) {
    List<X> items = items();
    for (X expectedItem : expectedItems) {
      if (!items.contains(expectedItem)) return false;
    }
    return true;
  }

  public boolean toContainInOrder(X... expectedItems) {
    int expectedIndex = 0;

    for (X x : actual) {
      if (eq(x, expectedItems[expectedIndex])) {
        expectedIndex++;
      }

      if (expectedIndex == expectedItems.length) {
        return true;
      }
    }
    return false;
  }

  public boolean toContainExactly(X... expectedItems) {
    int i = 0;

    for (X x : actual) {
      if (i >= expectedItems.length || !eq(x, expectedItems[i++])) {
        return false;
      }
    }
    return i == expectedItems.length;

  }

  public boolean toContainExactlyInAnyOrder(X... expectedItems) {
    if (expectedItems == null) {
      return false;
    }

    ObjectCounter<X> expectedItemsCounter = new ObjectCounter<X>();
    for (X expectedItem : expectedItems) {
      expectedItemsCounter.incrementCount(expectedItem);
    }

    for (X actualItem : actual) {
      if (expectedItemsCounter.getCount(actualItem) == 0) {
        return false;
      } else {
        expectedItemsCounter.decrementCount(actualItem);
      }
    }

    return expectedItemsCounter.allCountsAreZero();
  }

  public boolean toBeEmpty() {
    return !actual.iterator().hasNext();
  }

  private boolean eq(X actual, X expected) {
    return actual == null ? actual == expected : actual.equals(expected);
  }

  private List<X> items() {
    List<X> list = new ArrayList<X>();
    for (X x : actual) {
      list.add(x);
    }
    return list;
  }

  static class ObjectCounter<T> {
    private HashMap<T, Integer> countMap = new HashMap<T, Integer>();

    public boolean allCountsAreZero() {
      return countMap.keySet().isEmpty();
    }

    public int getCount(T key) {
      Integer count = countMap.get(key);
      return count == null ? 0 : count;
    }

    public void decrementCount(T key) {
      Integer count = countMap.get(key);
      if (count == null) {
        return;
      }
      if (count == 1) {
        countMap.remove(key);
      } else {
        countMap.put(key, count - 1);
      }
    }

    public void incrementCount(T key) {
      Integer count = countMap.get(key);
      countMap.put(key, count == null ? 1 : count + 1);
    }
  }

}
