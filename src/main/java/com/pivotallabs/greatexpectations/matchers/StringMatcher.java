package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.ExpectationOn;

@ExpectationOn(String.class)
public class StringMatcher<T extends String, M extends StringMatcher<T, M>> extends ObjectMatcher<T, M> {
  public boolean toContain(String expected) {
    return actual.indexOf(expected) != -1;
  }

//  public boolean toEqual(String expected) {
//    if (!GreatExpectations.equalsSafely(expected, actual) && !inverted) {
//      GreatExpectations.lastExpectTrace = null;
//      throw new ComparisonFailure("Expected strings to be equal to each other", expected, actual); // Shows friendly diff
//    }
//    return actual.equals(expected);
//  }

  public boolean toBeTrue() {
    return false;
  }

  public boolean toBeFalse(int a, String b, boolean c) {
    return false;
  }
}
