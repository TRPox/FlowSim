package irmb.test.model.geometry;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.Rectangle;
import irmb.test.presentation.ObserverSpy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by sven on 25.11.16.
 */
@RunWith(HierarchicalContextRunner.class)
public class RectangleTest {

    private Rectangle sut;

    @Before
    public void setUp() throws Exception {
        sut = new Rectangle();
        sut.setSecond(new Point(11, 12));
        sut.setFirst(new Point(21, 22));
    }

    @Test
    public void givenPointOnFirst_shouldReturnTrue() {
        assertTrue(sut.isPointOnBoundary(new Point(11, 12), 0));
    }

    @Test
    public void givenPointLeftOfFirst_shouldReturnFalse() {
        assertFalse(sut.isPointOnBoundary(new Point(10, 12), 0));
    }

    @Test
    public void givenPointBelowFirst_shouldReturnFalse() {
        assertFalse(sut.isPointOnBoundary(new Point(11, 10), 0));
    }

    @Test
    public void givenPointAboveSecond_shouldReturnFalse() {
        assertFalse(sut.isPointOnBoundary(new Point(21, 23), 0));
    }


    @Test
    public void givenPointRightOfSecond_shouldReturnFalse() {
        assertFalse(sut.isPointOnBoundary(new Point(22, 22), 0));
    }

    @Test
    public void givenPointInsideBoundingBoxAboveOfFirst_shouldReturnFalse() {
        assertFalse(sut.isPointOnBoundary(new Point(12, 13), 0));
    }

    @Test
    public void givenPointInsideBoundingBoxBelowSecond_shouldReturnFalse() {
        assertFalse(sut.isPointOnBoundary(new Point(20, 21), 0));
    }

    @Test
    public void givenPointOnUpperBoundary_shouldReturnTrue() {
        assertTrue(sut.isPointOnBoundary(new Point(15, 22), 0));
    }

    @Test
    public void givenPointOnLeftBoundary_shouldReturnTrue() {
        assertTrue(sut.isPointOnBoundary(new Point(11, 15), 0));
    }

    @Test
    public void givenPointOnRightBoundary_shouldReturnTrue() {
        assertTrue(sut.isPointOnBoundary(new Point(21, 15), 0));
    }

    @Test
    public void givenPointWithinRadius_shouldReturnTrue() {
        assertTrue(sut.isPointOnBoundary(new Point(8, 12), 3));
        assertTrue(sut.isPointOnBoundary(new Point(8, 17), 3));
        assertTrue(sut.isPointOnBoundary(new Point(24, 12), 3));
        assertTrue(sut.isPointOnBoundary(new Point(11, 9), 3));
        assertTrue(sut.isPointOnBoundary(new Point(16, 15), 3));
        assertTrue(sut.isPointOnBoundary(new Point(11, 25), 3));
        assertTrue(sut.isPointOnBoundary(new Point(16, 19), 3));
    }

    public class ObserverAddedContext {
        private ObserverSpy observerSpy;

        @Before
        public void setUp() {
            observerSpy = new ObserverSpy();
            sut.addObserver(observerSpy);
        }

        @Test
        public void whenMovingLine_shouldNotifyObserver() {
            sut.moveBy(5, 5);
            assertTrue(observerSpy.wasNotified());
        }
    }
}
