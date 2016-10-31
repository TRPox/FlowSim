package irmb.test.model.geometry;

import irmb.flowsim.model.geometry.Point;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sven on 20.10.2016.
 */
public class PointTest {
    @Test
    public void testToString() {
        String pointString = new Point(1, 2).toString();
        assertEquals("x: 1, y: 2", pointString);
    }
}