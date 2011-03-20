package com.pivotallabs.greatexpectations.expectors;

public class StringExpectationWrapper extends StringExpectation {
  public StringExpectationWrapper()
  {
  }

  public boolean toContain(String s)
  {
      return GreatExpectations.wrap(this, "toContain", super.toContain(s), new Object[] {
          s
      });
  }

  public boolean toEqual(Object obj)
  {
      return GreatExpectations.wrap(this, "toEqual", super.toEqual(obj), new Object[] {
          obj
      });
  }
}
