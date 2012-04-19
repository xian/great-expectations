package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.MatcherOf;
import java.util.Set;

@MatcherOf(value = Set.class, directObject = true)
public class SetMatcher<T extends Set<X>, X, M extends SetMatcher<T, X, M>> extends ObjectMatcher<T, M> {
  public boolean toContain(X... expectedItems) {
    for (X expectedItem : expectedItems) {
      if (!actual.contains(expectedItem)) return false;
    }
    return true;
  }

  public boolean toContainExactly(X... expectedItems) {
    for (X x : expectedItems) {
      if (!actual.contains(x)) {
        return false;
      }
    }
    return actual.size() == expectedItems.length;
  }

  public boolean toBeEmpty() {
    return actual.isEmpty();
  }

  public boolean toNumber(int expectedCount) {
    return actual.size() == expectedCount;
  }
}
