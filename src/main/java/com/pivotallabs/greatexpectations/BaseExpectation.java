package com.pivotallabs.greatexpectations;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;

public class BaseExpectation<T, SELF extends BaseExpectation<T, ?>> {
    public SELF not;
    protected T actual;
    protected boolean inverted;

    public BaseExpectation(T actual) {
        this.actual = actual;
    }

    public void toEqual(T expected) {
        match(Matchers.equalTo(expected));
    }

    public void toBe(T expected) {
        match(Matchers.sameInstance(expected));
    }

    public void toBeInstanceOf(Class<? extends T> expected) {
        match(Matchers.<T>instanceOf(expected));
    }

    public void toBeNull() {
        match((Matcher<T>) Matchers.nullValue());
    }

    protected void match(Matcher<T> matcher) {
        GreatExpectations.lastExpectTrace = null;
        Assert.assertThat(actual, inverted ? Matchers.not(matcher) : matcher);
    }

    public SELF setInvertedInstance(SELF invertedInstance) {
        com.pivotallabs.greatexpectations.GreatExpectations.checkForUnfinishedExpect();
        GreatExpectations.lastExpectTrace = new RuntimeException("you called expect() without a matcher!");

        not = invertedInstance;
        invertedInstance.inverted = true;
        return (SELF) this;
    }
}
