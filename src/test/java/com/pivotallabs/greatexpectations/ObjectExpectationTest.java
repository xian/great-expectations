package com.pivotallabs.greatexpectations;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ObjectExpectationTest {
    private ObjectExpectation<Object, ?> objectExpectation;

    @Before
    public void setUp() throws Exception {
        objectExpectation = new ObjectExpectation();
    }

    @Test
    public void toEqual_shouldReturnTrueIffObjectsAreEqual() throws Exception {
        objectExpectation.actual = "abc";
        assertEquals(true, objectExpectation.toEqual("abc"));
        assertEquals(false, objectExpectation.toEqual("def"));
    }

    @Test(expected = NullPointerException.class)
    public void toEqual_whenActualIsNull_shouldThrowNullPointerException() throws Exception {
        objectExpectation.actual = null;
        objectExpectation.toEqual("anything");
    }

    @Test(expected = NullPointerException.class)
    public void toEqual_whenActualAndExpectedAreNull_shouldThrowNullPointerException() throws Exception {
        objectExpectation.actual = null;
        objectExpectation.toEqual(null);
    }
}
