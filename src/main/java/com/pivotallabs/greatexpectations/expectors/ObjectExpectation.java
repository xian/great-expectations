package com.pivotallabs.greatexpectations.expectors;

import com.pivotallabs.greatexpectations.ExpectationOn;

@ExpectationOn(Object.class)
public class ObjectExpectation<T, M extends ObjectExpectation<T, M>> extends BaseExpectation<T, M> {
  public boolean toEqual(T expected) {
    if (actual == null) return false;
    return actual.equals(expected);
  }

  public boolean toBe(T expected) {
    return false;
//        match(Matchers.sameInstance(expected));
  }

  public boolean toBeInstanceOf(Class<? extends T> expected) {
    return false;
//        match(Matchers.<T>instanceOf(expected));
  }

  public boolean toBeNull() {
    return false;
//        match((Matcher<T>) Matchers.nullValue());
  }
}
