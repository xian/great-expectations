package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.MatcherOf;

@MatcherOf(Float.class)
public class FloatMatcher<T extends Float, M extends FloatMatcher<T, M>> extends ComparableMatcher<T, M> {
  public boolean toEqual(long expected) {
    return actual.floatValue() == expected;
  }

  public boolean toBeGreaterThan(long expected) {
    return actual.floatValue() > expected;
  }

  public boolean toBeLessThan(long expected) {
    return actual.floatValue() < expected;
  }

  public boolean toBeGreaterThan(double expected) {
    return actual.floatValue() > expected;
  }

  public boolean toBeLessThan(double expected) {
    return actual.floatValue() < expected;
  }

  public boolean toEqual(float expected, float delta) {
    return Math.abs(actual.floatValue() - expected) < delta;
  }
}
