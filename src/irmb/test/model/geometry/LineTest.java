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
    public void givenPointOnStart_shouldReturnTrue() {
        assertTrue(sut.isPointOnBoundary(makePoint(11, 12), 0));
    }

    @Test
    public void givenOrigin_shouldReturnFalse() {
        assertFalse(sut.isPointOnBoundary(makePoint(0, 0), 0));
    }

    @Test
    public void givenPointOnEnd_shouldReturnTrue() {
        assertTrue(sut.isPointOnBoundary(makePoint(21, 22), 0));
    }

    @Test
    public void givenPointAboveStart_shouldReturnFalse() {
        assertFalse(sut.isPointOnBoundary(makePoint(11, 13), 0));
    }

    @Test
    public void givenPointBelowEnd_shouldReturnFalse() {
        assertFalse(sut.isPointOnBoundary(makePoint(21, 21), 0));
    }

    @Test
    public void givenPointOnLine_shouldReturnTrue() {
        assertTrue(sut.isPointOnBoundary(makePoint(12, 13), 0));
        assertTrue(sut.isPointOnBoundary(makePoint(13, 14), 0));
        assertTrue(sut.isPointOnBoundary(makePoint(14, 15), 0));
        assertTrue(sut.isPointOnBoundary(makePoint(15, 16), 0));
        assertTrue(sut.isPointOnBoundary(makePoint(16, 17), 0));
        assertTrue(sut.isPointOnBoundary(makePoint(17, 18), 0));
        assertTrue(sut.isPointOnBoundary(makePoint(18, 19), 0));
        assertTrue(sut.isPointOnBoundary(makePoint(19, 20), 0));
        assertTrue(sut.isPointOnBoundary(makePoint(20, 21), 0));
    }

    @Test
    public void givenPointWithinAllowedRadius_shouldReturnTrue() {
        assertTrue(sut.isPointOnBoundary(makePoint(11, 13), 1));
        assertTrue(sut.isPointOnBoundary(makePoint(11, 13), 1));
    }

    private Point makePoint(int x, int y) {
        return new Point(x, y);
    }

}
