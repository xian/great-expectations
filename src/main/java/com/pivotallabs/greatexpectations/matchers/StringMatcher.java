package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.MatcherOf;

import java.util.regex.Pattern;

@MatcherOf(String.class)
public class StringMatcher<T extends String, M extends StringMatcher<T, M>> extends ObjectMatcher<T, M> {
  public boolean toContain(String expected) {
    return actual.indexOf(expected) != -1;
  }

  public boolean toStartWith(String expected) {
    return actual.startsWith(expected);
  }

  public boolean toEndWith(String expected) {
    return actual.endsWith(expected);
  }

  public boolean toMatch(String expectedPattern) {
    return Pattern.compile(expectedPattern).matcher(actual).find();
  }

//  public boolean toEqual(String expected) {
//    if (!GreatExpectations.equalsSafely(expected, actual) && !inverted) {
//      GreatExpectations.lastExpectTrace = null;
//      throw new ComparisonFailure("Expected strings to be equal to each other", expected, actual); // Shows friendly diff
//    }
//    return actual.equals(expected);
//  }
}
