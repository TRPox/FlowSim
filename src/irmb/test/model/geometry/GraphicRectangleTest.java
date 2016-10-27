package irmb.test.model.geometry;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.Rectangle;
import irmb.flowsim.view.GraphicRectangle;
import irmb.test.presentation.GraphicViewSpy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Sven on 25.10.2016.
 */
@RunWith(HierarchicalContextRunner.class)
public class GraphicRectangleTest {

    private GraphicViewSpy graphicViewSpy;
    private Rectangle rectangle;
    private GraphicRectangle graphicRectangle;

    @Before
    public void setUp() throws Exception {
        graphicViewSpy = new GraphicViewSpy();
        rectangle = new Rectangle();
        graphicRectangle = new GraphicRectangle(rectangle);
    }

    @Test
    public void whenAddingGraphicView_shouldNotNotifyGraphicView() {
        graphicRectangle.addObserver(graphicViewSpy);
        assertFalse(graphicViewSpy.wasNotified());
    }

    public class GraphicViewAddedContext {
        @Before
        public void setUp() {
            graphicRectangle.addObserver(graphicViewSpy);
        }

        @Test
        public void whenSettingFirst_shouldNotifyGraphicView() {
            rectangle.setFirst(new Point(1, 1));
            assertTrue(graphicViewSpy.wasNotified());
        }

        @Test
        public void whenSettingSecond_shouldNotifyGraphicView() {
            rectangle.setSecond(new Point(1, 1));
            assertTrue(graphicViewSpy.wasNotified());
        }
    }
}