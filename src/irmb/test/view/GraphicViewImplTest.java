package irmb.test.view;

import irmb.flowsim.presentation.GraphicView;
import irmb.flowsim.view.GraphicViewImpl;
import org.junit.Test;

/**
 * Created by Sven on 24.10.2016.
 */
public class GraphicViewImplTest {

    @Test
    public void canCreateGraphicViewImpl() {
        GraphicView view = new GraphicViewImpl();
    }

    @Test
    public void whenReceivingLine_shouldPaintLine() {
        
    }
}
