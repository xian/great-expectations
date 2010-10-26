package com.pivotallabs.greatexpectations;

import org.hamcrest.Matchers;
import org.junit.ComparisonFailure;

public class StringExpectation extends BaseExpectation<String, StringExpectation> {
    public StringExpectation(String actual) {
        super(actual);
    }

    public void toContain(String expected) {
        match(Matchers.containsString(expected));
    }

    @Override public void toEqual(String expected) {
        if (!GreatExpectations.equalsSafely(expected, actual) && !inverted) {
            GreatExpectations.lastExpectTrace = null;
            throw new ComparisonFailure("Expected strings to be equal to each other", expected, actual); // Shows friendly diff
        }
        match(Matchers.equalTo(expected));
    }
}
