package com.pivotallabs.greatexpectations.expectors;

import com.pivotallabs.greatexpectations.ExpectationOn;

@ExpectationOn(Boolean.class)
public class BooleanExpectation<T extends Boolean, M extends BooleanExpectation<T, M>> extends ObjectExpectation<T, M> {
  public boolean toBeTrue() {
    return actual.equals(true);
  }

  public boolean toBeFalse() {
    return actual.equals(false);
  }

  @Override public boolean toEqual(T expected) {
    return super.toEqual(expected);
  }
}
