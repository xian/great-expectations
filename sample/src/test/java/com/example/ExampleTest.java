package com.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.example.Expect.expect;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class ExampleTest {
  @Test
  public void expect_shouldCompile() throws Exception {
    expect(123).toBeGreaterThan(1);
    expect(Arrays.asList(1, 2, 3)).toContain(1);
  }

  @Test
  public void boolFail() throws Exception {
    expect(Boolean.TRUE).toBeTrue();
    expect(Boolean.TRUE).toBeFalse();
    expect(true).toEqual(false);
  }

  @Test
  public void stringCompare() throws Exception {
    expect("boo").toContain("oo");
    expect("boo").not.toContain("eek");
  }

  @Test
  public void stringEquals() throws Exception {
    expect("abc").toEqual("abc");
    expect("abc").not.toEqual("def");
  }

  @Test
  public void stringCompareFail() throws Exception {
    expect("this string").toContain("that string");
  }

  @Test
  public void iterableFail() throws Exception {
    expect(Arrays.asList("a", "b", "c")).toContain("d");
  }

  @Test
  public void shouldSuck() throws Exception {
    assertThat("team", not(containsString("me")));
  }

  @Test
  public void iterableContains() throws Exception {
    List<String> strings = Arrays.asList("1");
    expect(strings).toContain("1");
    expect(strings).not.toContain("2");
  }

  @Test
  public void numbersCanBeComparedInManyConvenientWays() throws Exception {
    // note: doubles and floats cannot be compared for equality, so we don't try here either
    expect(4.31f == 4.31d).toBeFalse();

    Integer intgr = new Integer(42);
    expect(42).toEqual(42);
    expect(intgr).toEqual(42);
    expect(intgr).toEqual(42L);
    expect(intgr).toEqual(42f);
    expect(intgr).toEqual(42d);
    expect(intgr).toBeGreaterThan(41);
    expect(intgr).toBeGreaterThan(41L);
    expect(intgr).toBeGreaterThan(41f);
    expect(intgr).toBeGreaterThan(41d);
    expect(intgr).toBeLessThan(43);
    expect(intgr).toBeLessThan(43L);
    expect(intgr).toBeLessThan(43f);
    expect(intgr).toBeLessThan(43d);

    Long lng = new Long(42);
    expect(42L).toEqual(42L);
    expect(lng).toEqual(42);
    expect(lng).toEqual(42L);
    expect(lng).toEqual(42f);
    expect(lng).toEqual(42d);
    expect(lng).toBeGreaterThan(41);
    expect(lng).toBeGreaterThan(41L);
    expect(lng).toBeGreaterThan(41f);
    expect(lng).toBeGreaterThan(41d);
    expect(lng).toBeLessThan(43);
    expect(lng).toBeLessThan(43L);
    expect(lng).toBeLessThan(43f);
    expect(lng).toBeLessThan(43d);

    Double dub34 = new Double(34);
    expect(dub34).toEqual(34);
    expect(dub34).toEqual(34L);
    expect(dub34).toEqual(34d);
    Double dub = new Double(34.41);
    expect(34.41).toEqual(34.41);
    expect(dub).not.toEqual(34);
    expect(dub).not.toEqual(34L);
    expect(dub).toEqual(34.41);
    expect(dub).toBeGreaterThan(34);
    expect(dub).toBeGreaterThan(34L);
    expect(dub).toBeGreaterThan(34f);
    expect(dub).toBeGreaterThan(34.3);
    expect(dub).toBeLessThan(35);
    expect(dub).toBeLessThan(35L);
    expect(dub).toBeLessThan(35f);
    expect(dub).toBeLessThan(34.42);

    Float flo34 = new Float(34);
    expect(flo34).toEqual(34);
    expect(flo34).toEqual(34L);
    expect(flo34).toEqual(34f);
    Float flo = new Float(34.41);
    expect(34.41f).toEqual(34.41f);
    expect(flo).not.toEqual(34);
    expect(flo).not.toEqual(34L);
    expect(flo).toEqual(34.41f);
    expect(flo).toBeGreaterThan(34);
    expect(flo).toBeGreaterThan(34L);
    expect(flo).toBeGreaterThan(34.3f);
    expect(flo).toBeGreaterThan(34.3);
    expect(flo).toBeLessThan(35);
    expect(flo).toBeLessThan(35L);
    expect(flo).toBeLessThan(34.42f);
    expect(flo).toBeLessThan(34.42);

    Float floatRoundOffError = new Float(2.2f + 1.1f);
    expect(floatRoundOffError).not.toEqual(3.3f);
    expect(floatRoundOffError).toEqual(3.3f, 0.000001f);

    Double doubleRoundOffError = new Double(2.2d + 1.1d);
    expect(doubleRoundOffError).not.toEqual(3.3d);
    expect(doubleRoundOffError).toEqual(3.3d, 0.000001d);
  }
}
