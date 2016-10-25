package irmb.test.model.geometry;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.PolyLine;
import irmb.test.presentation.GraphicViewSpy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by Sven on 25.10.2016.
 */
@RunWith(HierarchicalContextRunner.class)
public class PolyLineTest {

    private PolyLine polyLine;
    private GraphicViewSpy graphicViewSpy;

    @Before
    public void setUp() throws Exception {
        polyLine = new PolyLine();
        graphicViewSpy = new GraphicViewSpy();
    }


    @Test
    public void whenAddingGraphicView_shouldNotNotifyGraphicView() {
        polyLine.addGraphicView(graphicViewSpy);
        assertFalse(graphicViewSpy.wasNotified());
    }

    @Test
    public void whenAddingPoint_getPointListShouldContainPoint() {
        Point point = new Point(1, 2);
        polyLine.addPoint(point);
        Point addedPoint = polyLine.getPointList().get(0);
        assertExpectedPointEqualsActual(point, addedPoint);
    }

    private void assertExpectedPointEqualsActual(Point point, Point addedPoint) {
        assertEquals(point.getX(), addedPoint.getX());
        assertEquals(point.getY(), addedPoint.getY());
    }

    public class GraphicViewAddedContext {
        @Before
        public void setUp() {
            polyLine.addGraphicView(graphicViewSpy);
        }

        @Test
        public void whenAddingPoint_shouldNotifyGraphicView() {
            polyLine.addPoint(new Point(1, 1));
            assertTrue(graphicViewSpy.wasNotified());
        }
    }
}