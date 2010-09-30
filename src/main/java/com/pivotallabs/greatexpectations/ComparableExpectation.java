package com.pivotallabs.greatexpectations;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

public class ComparableExpectation<T extends Comparable<T>> extends BaseExpectation<T, ComparableExpectation<T>> {
    public ComparableExpectation(T actual) {
        super(actual);
    }

    public void toBeGreaterThan(T t) {
        match((Matcher<T>) Matchers.greaterThan(t));
    }
}
