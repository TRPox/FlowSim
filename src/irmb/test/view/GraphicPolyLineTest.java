package irmb.test.view;

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
public class GraphicPolyLineTest {

    private PolyLine polyLine;
    private GraphicPolyLineSpy graphicPolyLine;
    private GraphicViewSpy graphicViewSpy;

    @Before
    public void setUp() throws Exception {
        polyLine = new PolyLine();
        graphicPolyLine = new GraphicPolyLineSpy(polyLine);
        graphicViewSpy = new GraphicViewSpy();
    }


    @Test
    public void whenAddingGraphicView_shouldNotNotifyGraphicView() {
        graphicPolyLine.addObserver(graphicViewSpy);
        assertFalse(graphicViewSpy.wasNotified());
    }

    @Test
    public void whenAddingPoint_getPointListShouldContainPoint() {
        Point point = new Point(1, 2);
        polyLine.addPoint(point);
        Point addedPoint = graphicPolyLine.getPointList().get(0);
        assertExpectedPointEqualsActual(point, addedPoint);
    }

    private void assertExpectedPointEqualsActual(Point point, Point addedPoint) {
        assertEquals(point.getX(), addedPoint.getX());
        assertEquals(point.getY(), addedPoint.getY());
    }

    public class GraphicViewAddedContext {
        @Before
        public void setUp() {
            graphicPolyLine.addObserver(graphicViewSpy);
        }

        @Test
        public void whenAddingPoint_shouldNotifyGraphicView() {
            polyLine.addPoint(new Point(1, 1));
            assertTrue(graphicViewSpy.wasNotified());
        }
    }
}