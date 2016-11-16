package irmb.test.view;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.view.GraphicViewImpl;
import irmb.test.view.graphics.GraphicLineSpy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.swing.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by Sven on 24.10.2016.
 */
@RunWith(HierarchicalContextRunner.class)
public class GraphicViewImplTest {

    private JFrame frame;
    private GraphicViewImpl view;
    private GraphicLineSpy firstGraphicLine;
    private Line firstLine;

    @Before
    public void setUp() throws Exception {
        frame = new JFrame();
        view = new GraphicViewImpl();
        frame.add(view);
        frame.setVisible(true);
        firstLine = new Line();
        firstGraphicLine = new GraphicLineSpy(firstLine);
    }

    @Test
    public void whenReceivingLine_shouldPaintLine() {
//        try {
//            SwingUtilities.invokeAndWait(() -> view.receiveShape(firstGraphicLine));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        view.receiveShape(firstGraphicLine);
        assertTrue(firstGraphicLine.wasPainted());
    }


    @Test
    public void whenLineChanges_shouldRepaint() {
        view.receiveShape(firstGraphicLine);
        assertTrue(firstGraphicLine.wasPainted());
        Point point = new Point(1, 1);
        firstLine.setStart(point);
        assertEquals(2, firstGraphicLine.getTimesPainted());
    }

    public class OneLineAddedContext {
        private GraphicLineSpy secondGraphicLine;
        private Line secondLine;

        @Before
        public void setUp() {
            view.receiveShape(firstGraphicLine);
            secondLine = new Line();
            secondGraphicLine = new GraphicLineSpy(secondLine);
        }

        @Test
        public void whenReceivingNextLine_shouldPaintAllShapes() {
            view.receiveShape(secondGraphicLine);
            assertTrue(firstGraphicLine.wasPainted());
            assertTrue(secondGraphicLine.wasPainted());
        }

        public class TwoLinesAddedContext {
            @Before
            public void setUp() {
                view.receiveShape(secondGraphicLine);
            }

            @Test
            public void whenChangingFirstLine_shouldPaintAllShapes() {
                firstLine.setStart(new Point(1, 1));
                assertEquals(3, firstGraphicLine.getTimesPainted());
            }

            @Test
            public void whenRemovingLine_shouldRepaint() {
                view.removeShape(secondGraphicLine);
                assertEquals(3, firstGraphicLine.getTimesPainted());
                assertEquals(1, secondGraphicLine.getTimesPainted());
            }

            @Test
            public void whenChangingLineAfterRemoving_shouldNotRepaint() {
                view.removeShape(firstGraphicLine);
                firstLine.setStart(new Point(1, 1));
                assertEquals(2, firstGraphicLine.getTimesPainted());
                assertEquals(2, secondGraphicLine.getTimesPainted());
            }
        }
    }
}
