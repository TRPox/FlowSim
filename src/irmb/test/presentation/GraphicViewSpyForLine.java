package irmb.test.presentation;

import irmb.flowsim.model.geometry.Point;
import irmb.test.view.graphics.GraphicLineSpy;

/**
 * Created by Sven on 31.10.2016.
 */
public class GraphicViewSpyForLine extends GraphicViewSpy {

    public Point getFirst() {
        return ((GraphicLineSpy) receivedShape).getStart();
    }

    public Point getSecond() {
        return ((GraphicLineSpy) receivedShape).getEnd();
    }

}
