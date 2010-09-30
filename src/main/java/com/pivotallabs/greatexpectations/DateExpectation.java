package com.pivotallabs.greatexpectations;

import com.pivotallabs.greatexpectations.matchers.DateMatcher;

import java.util.Date;

public class DateExpectation extends BaseExpectation<Date, DateExpectation> {
    public DateExpectation(Date actual) {
        super(actual);
    }

    public void toBeLaterThan(Date expectedDateThreshold) {
        match(DateMatcher.isOlderThan(expectedDateThreshold));
    }

    public void toBeSoonerThan(Date expectedDateThreshold) {
        match(DateMatcher.isSoonerThan(expectedDateThreshold));
    }
}
