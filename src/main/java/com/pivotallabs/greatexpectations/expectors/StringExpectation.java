package com.pivotallabs.greatexpectations.expectors;

import com.pivotallabs.greatexpectations.ExpectationOn;

@ExpectationOn(String.class)
public class StringExpectation<T extends String, M extends StringExpectation<T, M>> extends ObjectExpectation<T, M> {
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
}
