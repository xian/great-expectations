package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.AllowActualToBeNull;
import com.pivotallabs.greatexpectations.BaseMatcher;
import com.pivotallabs.greatexpectations.MatcherOf;

@MatcherOf(Object.class)
public class ObjectMatcher<T, M extends ObjectMatcher<T, M>> extends BaseMatcher<T, M> {
  public boolean toEqual(T expected) {
    return actual.equals(expected);
  }

  public boolean toBeSameInstance(T expected) {
    return actual == expected;
  }

  public boolean toBeInstanceOf(Class<? extends T> expected) {
    return expected.isAssignableFrom(actual.getClass());
  }

  @AllowActualToBeNull
  public boolean toBeNull() {
    return actual == null;
  }
}
