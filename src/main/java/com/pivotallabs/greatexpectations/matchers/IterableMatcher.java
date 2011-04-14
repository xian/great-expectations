package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.MatcherOf;

import java.util.ArrayList;
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
      if (x.equals(expectedItems[expectedIndex])) {
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
      if (i >= expectedItems.length || !x.equals(expectedItems[i++])) {
        return false;
      }
    }
    return i == expectedItems.length;

  }

  public boolean toBeEmpty() {
    return !actual.iterator().hasNext();
  }

  private List<X> items() {
    List<X> list = new ArrayList<X>();
    for (X x : actual) {
      list.add(x);
    }
    return list;
  }
}
