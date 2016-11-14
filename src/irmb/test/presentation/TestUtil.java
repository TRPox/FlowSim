package irmb.test.presentation;

import irmb.flowsim.model.geometry.Point;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sven on 14.11.2016.
 */
public abstract class TestUtil {

    public static void assertExpectedPointEqualsActual(Point expected, Point actual) {
        assertEquals(expected.getX(), actual.getX(), 0.0001);
        assertEquals(expected.getY(), actual.getY(), 0.0001);
    }

}
