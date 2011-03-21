package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.ExpectationOn;

@ExpectationOn(Comparable.class)
public class ComparableMatcher<T extends Comparable, M extends ComparableMatcher<T, M>> extends ObjectMatcher<T, M> {
  public boolean toBeGreaterThan(T t) {
    return actual.compareTo(t) > 0;
//        match((Matcher<T>) Matchers.greaterThan(t));
  }
}
