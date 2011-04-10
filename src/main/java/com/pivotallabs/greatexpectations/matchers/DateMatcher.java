package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.MatcherOf;

import java.util.Date;

@MatcherOf(Date.class)
public class DateMatcher<T extends Date, M extends DateMatcher<T, M>> extends ComparableMatcher<T, M> {
  public boolean toBeLaterThan(T expectedDateThreshold) {
    return toBeGreaterThan(expectedDateThreshold);
  }

  public boolean toBeEarlierThan(T expectedDateThreshold) {
    return toBeLessThan(expectedDateThreshold);
  }
}
