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
  public void invertedStringCompare() throws Exception {
    expect("boo").toContain("oo");
    expect("boo").not.toContain("oo");
  }

  @Test
  public void stringEquals() throws Exception {
    expect("abc").toEqual("abc");
    expect("abc").toEqual("def");
  }

  @Test
  public void stringCompareFail() throws Exception {
    expect("this string").toContain("that string");
  }

  @Test
  public void iterable() throws Exception {
    expect(Arrays.asList("a", "b", "c")).toContain("d");
  }

  @Test
  public void shouldSuck() throws Exception {
    assertThat("team", not(containsString("me")));
  }

  @Test
  public void should() throws Exception {
    List<String> strings = Arrays.asList("1");
    expect(strings).toContain("1");
    expect(strings).not.toContain("1");
  }

  @Test
  public void numbers() throws Exception {
    Long theAnswer = new Long(42);
    expect(theAnswer).toEqual(42);
    expect(theAnswer).toEqual(42L);
    expect(theAnswer).toBeGreaterThan(41);
    expect(theAnswer).toBeGreaterThan(41L);
    expect(theAnswer).toBeLessThan(43);
    expect(theAnswer).toBeLessThan(43L);
  }
}
