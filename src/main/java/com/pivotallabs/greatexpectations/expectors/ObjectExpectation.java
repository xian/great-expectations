package com.pivotallabs.greatexpectations.expectors;

import com.pivotallabs.greatexpectations.ExpectationOn;

@ExpectationOn(Object.class)
public class ObjectExpectation<T, M extends ObjectExpectation<T, M>> extends BaseExpectation<T, M> {
  public boolean toEqual(T expected) {
    return actual.equals(expected);
  }

  public void toBe(T expected) {
//        match(Matchers.sameInstance(expected));
  }

  public void toBeInstanceOf(Class<? extends T> expected) {
//        match(Matchers.<T>instanceOf(expected));
  }

  public void toBeNull() {
//        match((Matcher<T>) Matchers.nullValue());
  }
}
