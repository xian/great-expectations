package com.pivotallabs.greatexpectations;

import com.pivotallabs.greatexpectations.matchers.*;
import static com.pivotallabs.greatexpectations.GreatExpectations.wrapped;

public class Expect {
  public static <T extends Object> ObjectMatcher<T, ?> expect(T actual) {
    //noinspection unchecked
    return wrapped(ObjectMatcher.class, actual);
  }
  public static BooleanMatcher<Boolean, ?> expect(boolean actual) {
    return wrapped(BooleanMatcher.class, actual);
  }
  public static <T extends Boolean> BooleanMatcher<T, ?> expect(T actual) {
    return wrapped(BooleanMatcher.class, actual);
  }
  public static <T extends Comparable> ComparableMatcher<T, ?> expect(T actual) {
    return wrapped(ComparableMatcher.class, actual);
  }
  public static <T extends java.util.Date> DateMatcher<T, ?> expect(T actual) {
    return wrapped(DateMatcher.class, actual);
  }
  public static <T extends Iterable<X>, X> IterableMatcher<T, X, ?> expect(T actual) {
    return wrapped(IterableMatcher.class, actual);
  }
  public static <T extends java.util.Set<X>, X> SetMatcher<T, X, ?> expect(T actual) {
    return wrapped(SetMatcher.class, actual);
  }
  public static LongMatcher<Long, ?> expect(long actual) {
    return wrapped(LongMatcher.class, actual);
  }
  public static <T extends Long, M> LongMatcher<T, ?> expect(T actual) {
    return wrapped(LongMatcher.class, actual);
  }
  public static <T extends String> StringMatcher<T, ?> expect(T actual) {
    return wrapped(StringMatcher.class, actual);
  }
}
