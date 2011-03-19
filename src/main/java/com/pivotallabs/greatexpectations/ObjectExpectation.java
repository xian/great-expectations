package com.pivotallabs.greatexpectations;

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
