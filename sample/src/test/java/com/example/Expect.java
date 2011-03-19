package com.example;

import com.pivotallabs.greatexpectations.expectors.BooleanExpectation;
import com.pivotallabs.greatexpectations.expectors.ComparableExpectation;
import com.pivotallabs.greatexpectations.expectors.DateExpectation;
import com.pivotallabs.greatexpectations.expectors.IterableExpectation;
import com.pivotallabs.greatexpectations.expectors.ObjectExpectation;
import com.pivotallabs.greatexpectations.expectors.StringExpectation;

import static com.pivotallabs.greatexpectations.expectors.GreatExpectations.wrapped;

public class Expect {
    public static <T extends Boolean, M extends BooleanExpectation<T, M>> BooleanExpectation<T, M> expect(T actual) {
        return wrapped(BooleanExpectation.class, actual);
    }
    public static <T extends Comparable, M extends ComparableExpectation<T, M>> ComparableExpectation<T, M> expect(T actual) {
        return wrapped(ComparableExpectation.class, actual);
    }
    public static <T extends java.util.Date, M extends DateExpectation<T, M>> DateExpectation<T, M> expect(T actual) {
        return wrapped(DateExpectation.class, actual);
    }
    public static <T extends Iterable<X>, X, M extends IterableExpectation<T, X, M>> IterableExpectation<T, X, M> expect(T actual) {
        return wrapped(IterableExpectation.class, actual);
    }
    public static <T extends Object, M extends ObjectExpectation<T, M>> ObjectExpectation<T, M> expect(T actual) {
        return wrapped(ObjectExpectation.class, actual);
    }
    public static <T extends String, M extends StringExpectation<T, M>> StringExpectation<T, M> expect(T actual) {
        return wrapped(StringExpectation.class, actual);
    }
}
