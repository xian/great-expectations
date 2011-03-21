package com.pivotallabs.greatexpectations.matchers;

import com.pivotallabs.greatexpectations.util.Transcript;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static com.pivotallabs.greatexpectations.matchers.GreatExpectations.wrapped;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
        newExpect(true).doFail("expectedValue");
        transcript.add("test is still running");
      }
    }, "Failure: Expected true doFail expectedValue");

    transcript.assertNothingSoFar();
  }

  @Test
  public void whenInvertedMatcherReturnsTrue_shouldStopTestWithFailure() throws Exception {
    expectFailure(new Runnable() {
      public void run() {
        newExpect(true).not.doPass("expectedValue");
        transcript.add("test is still running");
      }
    }, "Failure: Expected true not doPass expectedValue");

    transcript.assertNothingSoFar();
  }

  @Test
  public void whenExpectationThrowsException_shouldReportItWithNiceMessage() throws Exception {
    expectException(new Runnable() {
      public void run() {
        newExpect(true).doThrow(new RuntimeException("fake exception"));
        transcript.add("test is still running");
      }
    }, "java.lang.RuntimeException: fake exception");

    transcript.assertNothingSoFar();

    assertNull(GreatExpectations.lastExpectTrace);
  }

  @Ignore @Test
  public void whenExpectationThrowsException2_shouldReportItWithNiceMessage() throws Exception {
    newExpect(true).doThrow(new RuntimeException("fake exception"));
  }

  @Test
  public void shouldInvokeMethodsFromSuperclassesCorrectly() throws Exception {
    newExpect("abc").toFromSuper();

    expectFailure(new Runnable() {
      public void run() {
        newExpect("abc").not.toFromSuper();
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

  private static <T, M extends TestMatcher<T, M>> TestMatcher<T, M> newExpect(T actual) {
    return wrapped(TestMatcher.class, actual);
  }

  public static class OTestMatcher<T, M extends BaseMatcher<T, M>> extends BaseMatcher<T, M> {
    public boolean toFromSuper() {
      return true;
    }
  }

  public static class TestMatcher<T, M extends BaseMatcher<T, M>> extends OTestMatcher<T, M> {
    public boolean doPass(Object arg) {
      return true;
    }

    public boolean doFail(Object arg) {
      return false;
    }

    public boolean doThrow(Exception e) {
      throw new RuntimeException(e);
    }
  }
}
