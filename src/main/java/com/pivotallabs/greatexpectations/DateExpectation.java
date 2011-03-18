package com.pivotallabs.greatexpectations;

import java.util.Date;

public class DateExpectation<T extends Date, M extends DateExpectation<T, M>> extends ObjectExpectation<T, M> {
    public void toBeLaterThan(Date expectedDateThreshold) {
//        match(DateMatcher.isOlderThan(expectedDateThreshold));
    }

    public void toBeSoonerThan(Date expectedDateThreshold) {
//        match(DateMatcher.isSoonerThan(expectedDateThreshold));
    }
}
