package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.MatcherOf;

@MatcherOf(value = Long.class)
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
}
