package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.MatcherOf;

@MatcherOf(value = Iterable.class, directObject = true)
public class IterableMatcher<T extends Iterable<X>, X, M extends IterableMatcher<T, X, M>> extends BaseMatcher<T, M> {
  public boolean toContain(X... expectedItems) {
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

  public boolean toContainInAnyOrder(X... expectedItems) {
    return true;
//        match(Matchers.containsInAnyOrder(expectedItems));
  }

  public boolean toBeEmpty() {
    return !actual.iterator().hasNext();
  }
}
