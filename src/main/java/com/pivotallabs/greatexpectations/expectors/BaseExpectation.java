package com.pivotallabs.greatexpectations.expectors;

public class BaseExpectation<T, M extends BaseExpectation<T, M>> {
  public M not;
  protected T actual;
  protected boolean inverted;

  //    protected void match(Matcher<T> matcher) {
//        GreatExpectations.lastExpectTrace = null;
//        Assert.assertThat(actual, inverted ? Matchers.not(matcher) : matcher);
//    }
}
