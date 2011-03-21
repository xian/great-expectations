package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.MatcherOf;

@MatcherOf(Boolean.class)
public class BooleanMatcher<T extends Boolean, M extends BooleanMatcher<T, M>> extends ObjectMatcher<T, M> {
  public boolean toBeTrue() {
    return actual.equals(true);
  }

  public boolean toBeFalse() {
    return actual.equals(false);
  }

  @Override public boolean toEqual(T expected) {
    return super.toEqual(expected);
  }
}
