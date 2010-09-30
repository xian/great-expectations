package com.pivotallabs.greatexpectations;

public class GreatExpectations {
    public static RuntimeException lastExpectTrace = null;

    public static void checkForUnfinishedExpect() {
        if (lastExpectTrace != null) {
            RuntimeException e = lastExpectTrace;
            lastExpectTrace = null;
            throw e;
        }
    }

    public static boolean equalsSafely(String expected, String actual) {
        if (expected == null) {
            return actual == null;
        }
        return expected.equals(actual);
    }
}
