package com.pivotallabs.greatexpectations.expectors;

import com.pivotallabs.greatexpectations.ExpectationOn;

@ExpectationOn(Comparable.class)
public class ComparableExpectation<T extends Comparable, M extends ComparableExpectation<T, M>> extends ObjectExpectation<T, M> {
  public boolean toBeGreaterThan(T t) {
    return actual.compareTo(t) > 0;
//        match((Matcher<T>) Matchers.greaterThan(t));
  }
}
