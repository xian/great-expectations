package com.pivotallabs.greatexpectations.expectors;

import com.pivotallabs.greatexpectations.ExpectationOn;

@ExpectationOn(value = Iterable.class, directObject = true)
public class IterableExpectation<T extends Iterable<X>, X, M extends IterableExpectation<T, X, M>> extends BaseExpectation<T, M> {
  public boolean toContain(X... expectedItems) {
    return true;
//        match(Matchers.contains(expectedItems));
  }

  public boolean toContainInAnyOrder(X... expectedItems) {
    return true;
//        match(Matchers.containsInAnyOrder(expectedItems));
  }

  public boolean toHaveItems(X... expectedItems) {
    return true;
    //noinspection unchecked
//        match((Matcher) Matchers.hasItems(expectedItems));
  }

  public boolean toBeEmpty() {
    return true;
    //noinspection unchecked
//        match((Matcher) Matchers.emptyIterable());
  }
}
