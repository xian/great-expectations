package com.pivotallabs.greatexpectations.expectors;

import com.pivotallabs.greatexpectations.util.Transcript;
import org.junit.Before;
import org.junit.Test;

import static com.pivotallabs.greatexpectations.expectors.GreatExpectations.wrapped;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GreatExpectationsTest {
  private Transcript transcript;

  @Before
  public void setUp() throws Exception {
    transcript = new Transcript();
  }

  @Test
  public void whenMatcherReturnsFalse_shouldStopTestWithFailure() throws Exception {
    expectFailure(new Runnable() {
      public void run() {
        testExpect(true).doFail("expectedValue");
        transcript.add("test is still running");
      }
    }, "Failure: Expected true doFail expectedValue");

    transcript.assertNothingSoFar();
  }

  @Test
  public void whenInvertedMatcherReturnsTrue_shouldStopTestWithFailure() throws Exception {
    expectFailure(new Runnable() {
      public void run() {
        testExpect(true).not.doPass("expectedValue");
        transcript.add("test is still running");
      }
    }, "Failure: Expected true not doPass expectedValue");

    transcript.assertNothingSoFar();
  }

  @Test
  public void whenExpectationThrowsException2_shouldReportItWithNiceMessage() throws Exception {
    testExpect(true).doThrow(new RuntimeException("fake exception"));
  }

  @Test
  public void whenExpectationThrowsException_shouldReportItWithNiceMessage() throws Exception {
    expectException(new Runnable() {
      public void run() {
        testExpect(true).doThrow(new RuntimeException("fake exception"));
        transcript.add("test is still running");
      }
    }, "Error: fake exception\nExpected true doThrow false");

    transcript.assertNothingSoFar();
  }

  @Test
  public void shouldInvokeMethodsFromSuperclassesCorrectly() throws Exception {
    testExpect("abc").toFromSuper();

    expectFailure(new Runnable() {
      public void run() {
        testExpect("abc").not.toFromSuper();
        transcript.add("test is still running");
      }
    }, "Failure: Expected abc not toFromSuper");
    transcript.assertNothingSoFar();
  }

  ////////////////

  private void expectFailure(Runnable runnable, String expectedMessage) {
    AssertionError e = null;
    try {
      runnable.run();
    } catch (AssertionError e1) {
      e = e1;
    }
    assertNotNull("Expected AssertionError, didn't get one.", e);
    assertEquals(expectedMessage, e.getMessage());
  }

  private void expectException(Runnable runnable, String expectedMessage) {
    Throwable e = null;
    try {
      runnable.run();
    } catch (Throwable e1) {
      e = e1;
    }
    assertNotNull("Expected an exception, didn't get one.", e);
    assertEquals(expectedMessage, e.getMessage());
  }

  private static <T, M extends TestExpectation<T, M>> TestExpectation<T, M> testExpect(T actual) {
    return wrapped(TestExpectation.class, actual);
  }

  public static class OTestExpectation<T, M extends BaseExpectation<T, M>> extends BaseExpectation<T, M> {
    public boolean toFromSuper() {
      return true;
    }
  }

  public static class TestExpectation<T, M extends BaseExpectation<T, M>> extends OTestExpectation<T, M> {
    public boolean doPass(Object arg) {
      return true;
    }

    public boolean doFail(Object arg) {
      return false;
    }

    public boolean doThrow(RuntimeException e) {
      throw e;
    }
  }
}
