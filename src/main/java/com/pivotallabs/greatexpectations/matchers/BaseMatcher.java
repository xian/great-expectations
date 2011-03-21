package com.pivotallabs.greatexpectations.matchers;

public class BaseMatcher<T, M extends BaseMatcher<T, M>> {
  public M not;
  protected T actual;
  protected boolean inverted;

  //    protected void match(Matcher<T> matcher) {
//        GreatExpectations.lastExpectTrace = null;
//        Assert.assertThat(actual, inverted ? Matchers.not(matcher) : matcher);
//    }
}
