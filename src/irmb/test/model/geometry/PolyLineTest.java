package irmb.test.model.geometry;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.PolyLine;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by sven on 26.11.16.
 */
public class PolyLineTest {


    private PolyLine polyLine;

    @Before
    public void setUp() {
        polyLine = new PolyLine();
        polyLine.addPoint(new Point(11, 12));
        polyLine.addPoint(new Point(21, 22));
        polyLine.addPoint(new Point(45, 39));
    }

    @Test
    public void givenPointOnPolyLine_shouldReturnTrue() {
        assertTrue(polyLine.isPointOnBoundary(new Point(16, 17), 0));
    }

    @Test
    public void givenPointLeftOfFirstPoint_shouldReturnFalse() {
        assertFalse(polyLine.isPointOnBoundary(new Point(10, 12), 0));
    }


}
