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
  public void shouldGenerateNiceDefaultErrorMessages() throws Exception {
    expectFailure(new Runnable() {
      @Override public void run() {
        newExpect(Arrays.asList("a", "b", "c")).doFailWithArgs("d", "e");
      }
    }, "Failure: Expected <[a, b, c]> do fail with args <[d, e]>");
  }

  @Test
  public void shouldGenerateErrorMessageCustomizedByTheMatcherForActual() throws Exception {
    final ClassWithUselessToString actual = new ClassWithUselessToString("foo");
    assertEquals("bad toString", actual.toString());
    newExpect(actual).toHaveName("foo");
    expectFailure(new Runnable() {
      @Override public void run() {
        newExpect(actual).toHaveName("bar");
      }
    }, "Failure: Expected <custom actual message1 foo> to have name <bar>");
  }

  @Test
  public void shouldGenerateErrorMessageCustomizedByTheMatcherForExpected() throws Exception {
    final ClassWithUselessToString expected = new ClassWithUselessToString("bar");
    assertEquals("bad toString", expected.toString());
    newExpect(new ClassWithUselessToString("bar")).toHaveSameName(expected);
    expectFailure(new Runnable() {
      @Override public void run() {
        newExpect(new ClassWithUselessToString("foo")).toHaveSameName(expected);
      }
    }, "Failure: Expected <custom actual message2 foo> to have same name <custom expected message bar>");
  }

  @Test
  public void shouldGenerateErrorMessageForNullActual() throws Exception {
    expectFailure(new Runnable() {
      @Override public void run() {
        ClassWithUselessToString nullActual = null;
        newExpect(nullActual).not.allowActualNull();
      }
    }, "Failure: Expected <null> not allow actual null");
    expectFailure(new Runnable() {
      @Override public void run() {
        newExpect(new ClassWithUselessToString("foo")).allowActualNull();
      }
    }, "Failure: Expected <bad toString> allow actual null");
  }

  @Test
  public void shouldGenerateErrorMessageFullyCustomizedByTheMatcher() throws Exception {
    expectFailure(new Runnable() {
      @Override public void run() {
        newExpect(new ClassWithUselessToString("foo")).failWithFullyCustomizedError();
      }
    }, "Failure: Expected something to have something else");
    
    expectFailure(new Runnable() {
      @Override public void run() {
        newExpect(new ClassWithUselessToString("foo")).not.passWithFullyCustomizedError();
      }
    }, "Failure: Did not expect something to have something else");
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

  public static void expectFailure(Runnable runnable, String expectedMessage) {
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

  private static <T extends ClassWithUselessToString, M extends CustomMessageMatcher<T, M>> CustomMessageMatcher<T, M> newExpect(T actual) {
    return wrapped(CustomMessageMatcher.class, actual);
  }

  public static class CustomMessageMatcher<T extends ClassWithUselessToString, M extends BaseMatcher<T, M>> extends BaseMatcher<T, M> {
    public boolean toHaveName(String expectedName) {
      descriptionOfActual = "custom actual message1 " + actual.getName();
      return actual.getName().equals(expectedName);
    }

    public boolean toHaveSameName(ClassWithUselessToString expected) {
      descriptionOfActual = "custom actual message2 " + actual.getName();
      descriptionOfExpected = "custom expected message " + expected.getName();
      return actual.getName().equals(expected.getName());
    }

    public boolean failWithFullyCustomizedError() {
      failureMessage = "something to have something else";
      return false;
    }

    public boolean passWithFullyCustomizedError() {
      failureMessage = "something to have something else";
      return true;
    }

    @AllowActualToBeNull
    public boolean allowActualNull() {
      return actual == null;
    }
  }

  public static class ClassWithUselessToString {
    private String name;

    public ClassWithUselessToString(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    @Override
    public String toString() {
      return "bad toString";
    }
  }
}
