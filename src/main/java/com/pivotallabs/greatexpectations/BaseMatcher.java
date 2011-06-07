package com.pivotallabs.greatexpectations;

public class BaseMatcher<T, M extends BaseMatcher<T, M>> {
  public M not;
  protected T actual;
  protected boolean inverted;
  protected String descriptionOfActual;
  protected String descriptionOfExpected;
  protected String failureMessage;
}
