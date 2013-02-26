package com.pivotallabs.greatexpectations;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import org.junit.Test;

import static com.pivotallabs.greatexpectations.Expect.expect;

public class ExpectTest {
  @Test
  public void testObjectMatcher() throws Exception {
    Object nullObject = null;
    expect(nullObject).toBeNull();
    expect(new Object()).not.toBeNull();
  }

  @Test
  public void testBooleanMatcher_with_boolean() throws Exception {
    expect(false).toBeFalse();
    expect(false).not.toBeTrue();
  }

  @Test
  public void testBooleanMatcher_with_Object() throws Exception {
    expect(Boolean.FALSE).toBeFalse();
    expect(Boolean.FALSE).not.toBeTrue();
  }

  @Test
  public void testComparableMatcher() throws Exception {
    expect(1).toBeGreaterThan(0);
    expect(1).not.toBeGreaterThan(2);
  }

  @Test
  public void testDateMatcher() throws Exception {
    expect(new Date(20000)).toBeLaterThan(new Date(10000));
    // TODO: This isn't working
    //expect(new Date(20000)).not.toBeLaterThan(new Date(30000));
  }

  @Test
  public void testIterableMatcher() throws Exception {
    expect(Arrays.asList("a", "b", "c")).toContainExactly("a", "b", "c");
    expect(Arrays.asList("a", "b", "c")).not.toContainExactly("b", "c");
  }

  @Test
  public void testSetMatcher() throws Exception {
    HashSet<String> set = new HashSet<String>();
    Collections.addAll(set, "a", "b", "c");
    expect(set).toContain("b", "a", "c");
    expect(set).not.toContain("d", "e", "f");
  }

  @Test
  public void testLongMatcher_with_long() throws Exception {
    expect(42L).toEqual(42);
    expect(42L).not.toEqual(21);
  }

  @Test
  public void testLongMatcher_with_Object() throws Exception {
    expect(Long.valueOf(42L)).toEqual(42);
    expect(Long.valueOf(42L)).not.toEqual(21);
  }

  @Test
  public void testStringMatcher() throws Exception {
    expect("boo").toContain("oo");
    expect("boo").not.toContain("boot");
  }
}
