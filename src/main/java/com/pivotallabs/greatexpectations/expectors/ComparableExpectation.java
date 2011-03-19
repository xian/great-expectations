package com.pivotallabs.greatexpectations.expectors;

import com.pivotallabs.greatexpectations.ExpectationOn;

@ExpectationOn(Comparable.class)
public class ComparableExpectation<T extends Comparable<T>, M extends ComparableExpectation<T, M>> extends ObjectExpectation<T, M> {
  public void toBeGreaterThan(T t) {
//        match((Matcher<T>) Matchers.greaterThan(t));
  }
}
