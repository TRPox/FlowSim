package irmb.test.model.geometry;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.PolyLine;
import irmb.test.presentation.ObserverSpy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by sven on 26.11.16.
 */
@RunWith(HierarchicalContextRunner.class)
public class PolyLineTest {


    private PolyLine sut;

    @Before
    public void setUp() {
        sut = new PolyLine();
        sut.addPoint(makePoint(11, 12));
        sut.addPoint(makePoint(21, 22));
        sut.addPoint(makePoint(45, 39));
    }

    @Test
    public void givenPointOnPolyLine_shouldReturnTrue() {
        assertTrue(sut.isPointOnBoundary(makePoint(11, 12), 0));
        assertTrue(sut.isPointOnBoundary(makePoint(16, 17), 0));
        assertTrue(sut.isPointOnBoundary(makePoint(21, 22), 0));
        assertTrue(sut.isPointOnBoundary(makePoint(33, 30.5), 0));
        assertTrue(sut.isPointOnBoundary(makePoint(45, 39), 0));
    }

    @Test
    public void givenPointNotOnPolyLine_shouldReturnFalse() {
        assertFalse(sut.isPointOnBoundary(makePoint(10, 12), 0));
        assertFalse(sut.isPointOnBoundary(makePoint(16, 18), 0));
        assertFalse(sut.isPointOnBoundary(makePoint(22, 22), 0));
        assertFalse(sut.isPointOnBoundary(makePoint(33, 32), 0));
        assertFalse(sut.isPointOnBoundary(makePoint(33, 28), 0));
    }

    @Test
    public void givenPointWithinRadius_shouldReturnTrue() {
        assertTrue(sut.isPointOnBoundary(makePoint(11, 13), 1));
        assertTrue(sut.isPointOnBoundary(makePoint(11, 13), 1));
        assertTrue(sut.isPointOnBoundary(makePoint(12, 16), 3));
        assertTrue(sut.isPointOnBoundary(makePoint(14, 19), 3));

        assertTrue(sut.isPointOnBoundary(makePoint(46, 40), 3));
        assertTrue(sut.isPointOnBoundary(makePoint(45, 43), 3));
        assertTrue(sut.isPointOnBoundary(makePoint(48, 40), 3));
    }

    public class ObserverAddedContext {

        private ObserverSpy observerSpy;

        @Before
        public void setUp() {
            observerSpy = new ObserverSpy();
            sut.addObserver(observerSpy);
        }

        @Test
        public void whenMovingPolyLine_shouldNotifyObserver() {
            sut.moveBy(5, 5);
            assertTrue(observerSpy.wasNotified());
        }
    }

    private Point makePoint(double x, double y) {
        return new Point(x, y);
    }
}