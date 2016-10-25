package irmb.test.view;

import irmb.flowsim.presentation.GraphicView;
import irmb.flowsim.view.GraphicViewImpl;
import irmb.test.model.geometry.LineMock;
import org.junit.Test;

import javax.swing.*;

import java.lang.reflect.InvocationTargetException;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Sven on 24.10.2016.
 */
public class GraphicViewImplTest {

    @Test
    public void whenReceivingLine_shouldPaintLine() {
        JFrame frame = new JFrame();
        GraphicViewImpl view = new GraphicViewImpl();
        frame.add(view);
        frame.setVisible(true);
        LineMock lineMock = new LineMock();
        try {
            SwingUtilities.invokeAndWait(
                    new Runnable() {
                        @Override
                        public void run() {
                            view.receiveShape(lineMock);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(lineMock.wasPainted());
    }
}
