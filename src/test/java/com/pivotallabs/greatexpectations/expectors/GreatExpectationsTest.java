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
        expect(true).doFail(false);
        transcript.add("test is still running");
      }
    }, "Failure: Expected true doFail false");

    transcript.assertNothingSoFar();
  }

  @Test
  public void whenExpectationThrowsException_shouldReportItWithNiceMessage() throws Exception {
    expectException(new Runnable() {
      public void run() {
        expect(true).doThrow(new RuntimeException("fake exception"));
        transcript.add("test is still running");
      }
    }, "Error: fake exception\nExpected true doThrow false");

    transcript.assertNothingSoFar();
  }

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

  private static <T, M extends TestExpectation<T, M>> TestExpectation<T, M> expect(T actual) {
    return wrapped(TestExpectation.class, actual);
  }

  public static class TestExpectation<T, M extends BaseExpectation<T, M>> extends BaseExpectation<T, M> {
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
