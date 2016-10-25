package irmb.test.view;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.view.GraphicViewImpl;
import irmb.test.model.geometry.LineSpy;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by Sven on 24.10.2016.
 */
public class GraphicViewImplTest {

    private JFrame frame;
    private GraphicViewImpl view;
    private LineSpy first;

    @Before
    public void setUp() throws Exception {
        frame = new JFrame();
        view = new GraphicViewImpl();
        frame.add(view);
        frame.setVisible(true);
        first = new LineSpy();
    }

    @Test
    public void whenReceivingLine_shouldPaintLine() {
        //        try {
//            SwingUtilities.invokeAndWait(
//                    new Runnable() {
//                        @Override
//                        public void run() {
//                            view.receiveShape(first);
//                        }
//                    });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        view.receiveShape(first);
        assertTrue(first.wasPainted());
    }

    @Test
    public void whenReceivingMultipleLines_shouldPaintAllShapes() {
        LineSpy second = new LineSpy();
        view.receiveShape(first);
        view.receiveShape(second);
        assertTrue(first.wasPainted());
        assertTrue(second.wasPainted());
    }

    @Test
    public void whenLineChanges_shouldRepaint() {
        view.receiveShape(first);
        assertTrue(first.wasPainted());

        Point point = new Point(1,1);
        first.setStart(point);
        assertEquals(2, first.getTimesPainted());
    }
}
