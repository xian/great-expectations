package com.example;

import org.junit.Test;

import java.util.Arrays;

import static com.example.Expect.expect;

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
}
