package com.pivotallabs.greatexpectations.expectors;

import com.pivotallabs.greatexpectations.ExpectationOn;

@ExpectationOn(value = Iterable.class, directObject = true)
public class IterableExpectation<T extends Iterable<X>, X, M extends IterableExpectation<T, X, M>> extends BaseExpectation<T, M> {
  public void toContain(X... expectedItems) {
//        match(Matchers.contains(expectedItems));
  }

  public void toContainInAnyOrder(X... expectedItems) {
//        match(Matchers.containsInAnyOrder(expectedItems));
  }

  public void toHaveItems(X... expectedItems) {
    //noinspection unchecked
//        match((Matcher) Matchers.hasItems(expectedItems));
  }

  public void toBeEmpty() {
    //noinspection unchecked
//        match((Matcher) Matchers.emptyIterable());
  }
}
