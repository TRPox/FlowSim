package irmb.test.presentation;

import irmb.flowsim.model.geometry.Point;
import irmb.test.view.graphics.GraphicRectangleSpy;

/**
 * Created by Sven on 31.10.2016.
 */
public class GraphicViewSpyForRectangle extends GraphicViewSpy {

    public Point getFirst() {
        return ((GraphicRectangleSpy) receivedShape).getFirst();
    }

    public Point getSecond() {
        return ((GraphicRectangleSpy) receivedShape).getSecond();
    }

}
