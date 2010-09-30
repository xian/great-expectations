package com.pivotallabs.greatexpectations;

import org.hamcrest.Matchers;

public class BooleanExpectation extends BaseExpectation<Boolean, BooleanExpectation> {
    public BooleanExpectation(Boolean actual) {
        super(actual);
    }

    public void toBeTrue() {
        match(Matchers.equalTo(true));
    }

    public void toBeFalse() {
        match(Matchers.equalTo(false));
    }
}
