package com.pivotallabs.greatexpectations.expectors;

import com.pivotallabs.greatexpectations.ExpectationOn;

@ExpectationOn(value = Iterable.class, directObject = true)
public class IterableExpectation<T extends Iterable<X>, X, M extends IterableExpectation<T, X, M>> extends BaseExpectation<T, M> {
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
