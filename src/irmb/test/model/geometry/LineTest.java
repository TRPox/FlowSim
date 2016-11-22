package irmb.test.model.geometry;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.Point;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Sven on 22.11.2016.
 */
public class LineTest {

    private Line sut;
    private Point start;
    private Point end;

    @Before
    public void setUp() throws Exception {
        sut = new Line();
        start = makePoint(11, 12);
        end = makePoint(21, 22);
        sut.setStart(start);
        sut.setEnd(end);
    }

    @Test
    public void testIsPointOnBoundaryWithPointOnLine() {


        assertTrue(sut.isPointOnBoundary(makePoint(11, 12)));
//        assertTrue(sut.isPointOnBoundary(makePoint(12, 13)));
//        assertTrue(sut.isPointOnBoundary(makePoint(13, 14)));
//        assertTrue(sut.isPointOnBoundary(makePoint(14, 15)));
    }


    @Test
    public void testIsPointOnLineWithPointNotOnLine() {
        assertFalse(sut.isPointOnBoundary(makePoint(0, 0)));
        assertFalse(sut.isPointOnBoundary(makePoint(1, 0)));
        assertFalse(sut.isPointOnBoundary(makePoint(11, 0)));
        assertFalse(sut.isPointOnBoundary(makePoint(11, 23)));
        assertFalse(sut.isPointOnBoundary(makePoint(22, 12)));
        assertFalse(sut.isPointOnBoundary(makePoint(11, 13)));
    }

    private Point makePoint(int x, int y) {
        return new Point(x, y);
    }

}
