package com.pivotallabs.greatexpectations.expectors;

import com.pivotallabs.greatexpectations.ExpectationOn;

import java.util.Date;

@ExpectationOn(Date.class)
public class DateExpectation<T extends Date, M extends DateExpectation<T, M>> extends ObjectExpectation<T, M> {
  public void toBeLaterThan(Date expectedDateThreshold) {
//        match(DateMatcher.isOlderThan(expectedDateThreshold));
  }

  public void toBeSoonerThan(Date expectedDateThreshold) {
//        match(DateMatcher.isSoonerThan(expectedDateThreshold));
  }
}
