package com.pivotallabs.greatexpectations;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import org.junit.Test;

public class ExpectTest {
  @Test
  public void testObjectMatcher() throws Exception {
    Object nullObject = null;
    Expect.expect(nullObject).toBeNull();
  }

  @Test
  public void testBooleanMatcher_with_boolean() throws Exception {
    Expect.expect(false).toBeFalse();
  }

  @Test
  public void testBooleanMatcher_with_Object() throws Exception {
    Expect.expect(Boolean.FALSE).toBeFalse();
  }

  @Test
  public void testComparableMatcher() throws Exception {
    Expect.expect(1).toBeGreaterThan(0);
  }

  @Test
  public void testDateMatcher() throws Exception {
    Expect.expect(new Date(20000)).toBeLaterThan(new Date(10000));
  }

  @Test
  public void testIterableMatcher() throws Exception {
    Expect.expect(Arrays.asList("a", "b", "c")).toContainExactly("a", "b", "c");
  }

  @Test
  public void testSetMatcher() throws Exception {
    HashSet<String> set = new HashSet<String>();
    Collections.addAll(set, "a", "b", "c");
    Expect.expect(set).toContain("b", "a", "c");
  }

  @Test
  public void testLongMatcher() throws Exception {
    Expect.expect(42L).toEqual(42);
  }

  @Test
  public void testStringMatcher() throws Exception {
    Expect.expect("abc").toMatch("b");
  }
}
