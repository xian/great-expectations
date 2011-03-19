package com.example;

import com.pivotallabs.greatexpectations.expectors.IterableExpectation;
import org.junit.Test;

import java.util.Arrays;

import static com.example.Expect.expect;

public class ExampleTest {
  @Test
  public void expect_shouldCompile() throws Exception {
    expect(Boolean.FALSE).toBeTrue();
    expect("boo").toContain("oo");
    expect(123).toBeGreaterThan(1);
    expect(Arrays.asList(1, 2, 3)).toContain(1);
  }

  @Test
  public void should() throws Exception {
    IterableExpectation<Iterable<Integer>, Integer, ?> e = null;
    IterableExpectation<Iterable<Integer>, Integer, ?> not = e.not;

    e.toContain(1);
  }

  @Test
  public void boolFail() throws Exception {
    expect(Boolean.TRUE).toBeFalse();
  }
}
