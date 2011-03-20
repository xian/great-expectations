package com.example;

import org.junit.Test;

import java.util.Arrays;

import static com.example.Expect.expect;

public class ExampleTest {
  @Test
  public void expect_shouldCompile() throws Exception {
    expect(Boolean.FALSE).toBeTrue();
    expect(123).toBeGreaterThan(1);
    expect(Arrays.asList(1, 2, 3)).toContain(1);
  }

  @Test
  public void boolFail() throws Exception {
    expect(Boolean.TRUE).toBeFalse();
  }

  @Test
  public void invertedStringCompare() throws Exception {
    expect("boo").not.toContain("oo");
  }

  @Test
  public void stringEquals() throws Exception {
    expect("abc").toEqual("def");
  }

  @Test
  public void stringCompareFail() throws Exception {
    expect("this string").toContain("that string");
  }
}
