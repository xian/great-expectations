package com.pivotallabs.greatexpectations;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

public class IterableExpectation<T extends Iterable<?>, M extends ObjectExpectation<T, M>> extends BaseExpectation<T, M> {
    public void toContain(T... expectedItems) {
//        match(Matchers.contains(expectedItems));
    }

    public void toContainInAnyOrder(T... expectedItems) {
//        match(Matchers.containsInAnyOrder(expectedItems));
    }

    public void toHaveItems(T... expectedItems) {
        //noinspection unchecked
        match((Matcher) Matchers.hasItems(expectedItems));
    }

    public void toBeEmpty() {
        //noinspection unchecked
        match((Matcher) Matchers.emptyIterable());
    }
}
