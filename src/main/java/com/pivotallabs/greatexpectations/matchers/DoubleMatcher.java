package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.MatcherOf;

@MatcherOf(Double.class)
public class DoubleMatcher<T extends Double, M extends DoubleMatcher<T, M>> extends ComparableMatcher<T, M> {
  public boolean toEqual(long expected) {
    return actual.doubleValue() == expected;
  }

  public boolean toBeGreaterThan(long expected) {
    return actual.doubleValue() > expected;
  }

  public boolean toBeLessThan(long expected) {
    return actual.doubleValue() < expected;
  }

  public boolean toBeGreaterThan(float expected) {
    return actual.doubleValue() > expected;
  }

  public boolean toBeLessThan(float expected) {
    return actual.doubleValue() < expected;
  }

  public boolean toEqual(double expected, double delta) {
    return Math.abs(actual.doubleValue() - expected) < delta;
  }
}
