package com.example;

import com.pivotallabs.greatexpectations.matchers.BooleanMatcher;
import com.pivotallabs.greatexpectations.matchers.ComparableMatcher;
import com.pivotallabs.greatexpectations.matchers.IterableMatcher;
import com.pivotallabs.greatexpectations.matchers.ObjectMatcher;
import com.pivotallabs.greatexpectations.matchers.StringMatcher;

import static com.pivotallabs.greatexpectations.matchers.GreatExpectations.wrapped;

public class Expect {
    public static <T extends Boolean, M extends BooleanMatcher<T, M>> BooleanMatcher<T, M> expect(T actual) {
        return wrapped(BooleanMatcher.class, actual);
    }
    public static <T extends Comparable, M extends ComparableMatcher<T, M>> ComparableMatcher<T, M> expect(T actual) {
        return wrapped(ComparableMatcher.class, actual);
    }
    public static <T extends java.util.Date, M extends DateMatcher<T, M>> DateMatcher<T, M> expect(T actual) {
        return wrapped(DateMatcher.class, actual);
    }
    public static <T extends Iterable<X>, X, M extends IterableMatcher<T, X, M>> IterableMatcher<T, X, M> expect(T actual) {
        return wrapped(IterableMatcher.class, actual);
    }
    public static <T extends Object, M extends ObjectMatcher<T, M>> ObjectMatcher<T, M> expect(T actual) {
        return wrapped(ObjectMatcher.class, actual);
    }
    public static <T extends String, M extends StringMatcher<T, M>> StringMatcher<T, M> expect(T actual) {
        return wrapped(StringMatcher.class, actual);
    }
}
