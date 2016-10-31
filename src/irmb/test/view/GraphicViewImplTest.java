package irmb.test.view;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.view.GraphicViewImpl;
import irmb.test.model.geometry.GraphicLineSpy;
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
    private GraphicLineSpy first;
    private Line line;

    @Before
    public void setUp() throws Exception {
        frame = new JFrame();
        view = new GraphicViewImpl();
        frame.add(view);
        frame.setVisible(true);
        line = new Line();
        first = new GraphicLineSpy(line);
    }

    @Test
    public void whenReceivingLine_shouldPaintLine() {
        //        try {
//            SwingUtilities.invokeAndWait(
//                    new Runnable() {
//                        @Override
//                        public void run() {
//                            observer.receiveShape(first);
//                        }
//                    });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        view.receiveShape(first);
        assertTrue(first.wasPainted());
    }


    @Test
    public void whenLineChanges_shouldRepaint() {
        view.receiveShape(first);
        assertTrue(first.wasPainted());
        Point point = new Point(1, 1);
        line.setStart(point);
        assertEquals(2, first.getTimesPainted());
    }

    public class OneLineAddedContext {
        private GraphicLineSpy second;

        @Before
        public void setUp() {
            view.receiveShape(first);
            second = new GraphicLineSpy(new Line());
        }

        @Test
        public void whenReceivingNextLine_shouldPaintAllShapes() {
            view.receiveShape(second);
            assertTrue(first.wasPainted());
            assertTrue(second.wasPainted());
        }


        public class TwoLinesAddedContext {
            @Before
            public void setUp() {
                view.receiveShape(second);
            }

            @Test
            public void whenChangingFirstLine_shouldPaintAllShapes() {
                line.setStart(new Point(1, 1));
                assertEquals(3, first.getTimesPainted());
            }
        }
    }
}
