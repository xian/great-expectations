package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.MatcherOf;

import java.util.Date;

@MatcherOf(Date.class)
public class DateMatcher<T extends Date, M extends DateMatcher<T, M>> extends ObjectMatcher<T, M> {
  public void toBeLaterThan(Date expectedDateThreshold) {
//        match(DateMatcher.isOlderThan(expectedDateThreshold));
  }

  public void toBeSoonerThan(Date expectedDateThreshold) {
//        match(DateMatcher.isSoonerThan(expectedDateThreshold));
  }
}


//package com.pivotallabs.greatexpectations.matchers;
//
//import org.hamcrest.Description;
//import org.hamcrest.Matcher;
//import org.hamcrest.TypeSafeMatcher;
//
//import java.util.Date;
//
//public class DateMatcher extends TypeSafeMatcher<Date> {
//    String message = "";
//    Date expectedThresholdDate;
//
//    public DateMatcher(Date expectedThresholdDate) {
//        this.expectedThresholdDate = expectedThresholdDate;
//    }
//
//
//    @Override public void describeTo(Description description) {
//        description.appendText(message);
//    }
//
//    @Override protected boolean matchesSafely(Date actual) {
//        if (actual == null) {
//            message = "to get a non-null date";
//            return false;
//        }
//
//        return matchesNonNull(actual);
//    }
//
//    protected boolean matchesNonNull(Date actual) {
//        message = "to get " + expectedThresholdDate.toString();
//        return actual.equals(expectedThresholdDate);
//    }
//
//    public static Matcher<Date> isOlderThan(Date expectedThresholdDate) {
//        return new DateMatcher(expectedThresholdDate) {
//            @Override protected boolean matchesNonNull(Date actual) {
//                message = "to get a date later than " + expectedThresholdDate;
//                return actual.after(expectedThresholdDate);
//            }
//        };
//    }
//
//    public static Matcher<Date> isSoonerThan(Date expectedThresholdDate) {
//        return new DateMatcher(expectedThresholdDate) {
//            @Override protected boolean matchesNonNull(Date actual) {
//                message = "to get a date earlier than " + expectedThresholdDate;
//                return actual.before(expectedThresholdDate);
//            }
//        };
//    }
//}
