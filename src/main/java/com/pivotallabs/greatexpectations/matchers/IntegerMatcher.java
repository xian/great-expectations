package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.MatcherOf;

@MatcherOf(Integer.class)
public class IntegerMatcher<T extends Integer, M extends IntegerMatcher<T, M>> extends ComparableMatcher<T, M> {
  public boolean toEqual(long expected) {
    return actual.intValue() == expected;
  }

  public boolean toBeGreaterThan(long expected) {
    return actual.intValue() > expected;
  }

  public boolean toBeLessThan(long expected) {
    return actual.intValue() < expected;
  }

  public boolean toEqual(double expected) {
    return actual.intValue() == expected;
  }

  public boolean toBeGreaterThan(double expected) {
    return actual.intValue() > expected;
  }

  public boolean toBeLessThan(double expected) {
    return actual.intValue() < expected;
  }
}
