package com.pivotallabs.greatexpectations;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

public class IterableExpectation<T> extends BaseExpectation<Iterable<? extends T>, IterableExpectation<T>> {
    public IterableExpectation(Iterable<T> actual) {
        super(actual);
    }

    public void toContain(T... expectedItems) {
        match(Matchers.contains(expectedItems));
    }

    public void toBeEmpty() {
        //noinspection unchecked
        match((Matcher) Matchers.emptyIterable());
    }
}
