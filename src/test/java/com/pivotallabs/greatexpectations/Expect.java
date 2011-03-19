package com.pivotallabs.greatexpectations;

import java.util.Date;

import static com.pivotallabs.greatexpectations.GreatExpectations.wrapped;

public class Expect {
  private static <T extends Boolean, M extends BooleanExpectation<T, M>> BooleanExpectation<T, M> expect(T actual) {
    return wrapped(BooleanExpectation.class, actual);
  }

  private static <T extends Comparable<T>, M extends ComparableExpectation<T, M>> ComparableExpectation<T, M> expect(T actual) {
    return wrapped(ComparableExpectation.class, actual);
  }

  private static <T extends Date, M extends DateExpectation<T, M>> DateExpectation<T, M> expect(T actual) {
    return wrapped(DateExpectation.class, actual);
  }

  private static <T, M extends ObjectExpectation<T, M>> ObjectExpectation<T, M> expect(T actual) {
    return wrapped(ObjectExpectation.class, actual);
  }

  private static <T extends String, M extends StringExpectation<T, M>> StringExpectation<T, M> expect(T actual) {
    return wrapped(StringExpectation.class, actual);
  }
}