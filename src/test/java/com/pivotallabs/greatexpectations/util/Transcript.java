package com.pivotallabs.greatexpectations.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Transcript {
    List<String> events = new ArrayList<String>();

    public void add(String event) {
        events.add(event);
    }

    public void assertNothingSoFar() {
        assertEquals(Arrays.<String>asList(), events);
    }
}
