package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.MatcherOf;

@MatcherOf(Long.class)
public class LongMatcher<T extends Long, M extends LongMatcher<T, M>> extends ComparableMatcher<T, M> {
  public boolean toEqual(int expected) {
    return actual.longValue() == expected;
  }

  public boolean toBeGreaterThan(int expected) {
    return actual.longValue() > expected;
  }

  public boolean toBeLessThan(int expected) {
    return actual.longValue() < expected;
  }

  public boolean toEqual(double expected) {
    return actual.longValue() == expected;
  }

  public boolean toBeGreaterThan(double expected) {
    return actual.longValue() > expected;
  }

  public boolean toBeLessThan(double expected) {
    return actual.longValue() < expected;
  }
}
