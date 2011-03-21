package com.pivotallabs.greatexpectations.expectors;

import com.pivotallabs.greatexpectations.ExpectationOn;

@ExpectationOn(Object.class)
public class ObjectExpectation<T, M extends ObjectExpectation<T, M>> extends BaseExpectation<T, M> {
  public boolean toEqual(T expected) {
    return actual.equals(expected);
  }

  public boolean toBe(T expected) {
    if (actual == null) throw new NullPointerException("actual is null");
    return actual == expected;
  }

  public boolean toBeInstanceOf(Class<? extends T> expected) {
    return expected.isAssignableFrom(actual.getClass());
  }

  public boolean toBeNull() {
    return actual == null;
  }
}
