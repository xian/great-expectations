package com.pivotallabs.greatexpectations;

import com.pivotallabs.greatexpectations.util.Transcript;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static com.pivotallabs.greatexpectations.GreatExpectations.wrapped;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

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
      }
    }, "Failure: Expected <true> do fail <expectedValue>");
    assertNull(GreatExpectations.lastExpectTrace);
  }

  @Test
  public void whenInvertedMatcherReturnsTrue_shouldStopTestWithFailure() throws Exception {
    expectFailure(new Runnable() {
      public void run() {
        newExpect(true).not.doPass("expectedValue");
      }
    }, "Failure: Expected <true> not do pass <expectedValue>");
    assertNull(GreatExpectations.lastExpectTrace);
  }

  @Test
  public void whenExpectationThrowsException_shouldReportItWithNiceMessage() throws Exception {
    expectException(new Runnable() {
      public void run() {
        newExpect(true).doThrow(new RuntimeException("fake exception"));
      }
    }, "java.lang.RuntimeException: fake exception");
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
      }
    }, "Failure: Expected <abc> not to from super");
  }

  @Test
  public void shouldGenerateNiceErrorMessages() throws Exception {
    expectFailure(new Runnable() {
      @Override public void run() {
        newExpect(Arrays.asList("a", "b", "c")).doFailWithArgs("d", "e");
      }
    }, "Failure: Expected <[a, b, c]> do fail with args <[d, e]>");
  }

  @Test
  public void shouldNotAllowActualToBeNull() throws Exception {
    expectException(new Runnable() {
      @Override public void run() {
        newExpect((Object) null).doPass("anything");
      }
    }, "actual should not be null");
  }

  @Test
  public void whenAllowActualToBeNull_shouldAllowActualToBeNull() throws Exception {
    newExpect((Object) null).doPermitNullActual();
  }

  ////////////////

  private void expectFailure(Runnable runnable, String expectedMessage) {
    AssertionError e = null;
    try {
      runnable.run();
      fail("shouldn't have gotten here!");
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

    public boolean doFailWithArgs(String... strings) {
      return false;
    }

    @AllowActualToBeNull
    public boolean doPermitNullActual() {
      return true;
    }
  }
}
