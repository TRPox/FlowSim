package irmb.test.presentation;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.GraphicView;
import irmb.flowsim.view.GraphicShape;
import irmb.test.model.geometry.GraphicLineSpy;

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
