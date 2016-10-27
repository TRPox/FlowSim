package irmb.test.model.geometry;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.view.GraphicLine;
import irmb.test.presentation.GraphicViewSpy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Sven on 25.10.2016.
 */
@RunWith(HierarchicalContextRunner.class)
public class GraphicLineTest {

    private Line line;
    private GraphicLine graphicLine;
    private GraphicViewSpy graphicViewSpy;

    @Before
    public void setUp() throws Exception {
        line = new Line();
        graphicLine = new GraphicLine(line);
        graphicViewSpy = new GraphicViewSpy();
    }

    @Test
    public void whenAddingGraphicView_shouldNotNotifyGraphicView() {
        graphicLine.addObserver(graphicViewSpy);
        assertFalse(graphicViewSpy.wasNotified());
    }

    public class GraphicViewAddedContext {
        @Before
        public void setUp() {
            graphicLine.addObserver(graphicViewSpy);
        }

        @Test
        public void whenSettingStartShouldNotifyGraphicView() {
            line.setStart(new Point(1, 1));
            assertTrue(graphicViewSpy.wasNotified());
        }

        @Test
        public void whenSettingEnd_shouldNotifiyGraphicView() {
            line.setEnd(new Point(1, 1));
            assertTrue(graphicViewSpy.wasNotified());
        }
    }
}