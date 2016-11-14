package irmb.test.presentation;

import irmb.flowsim.model.geometry.Point;
import irmb.test.view.GraphicPolyLineSpy;

import java.util.List;

/**
 * Created by Sven on 31.10.2016.
 */
public class GraphicViewSpyForPolyLine extends GraphicViewSpy {

    public List<Point> getReceivedPolyLinePointList() {
        return ((GraphicPolyLineSpy) receivedShape).getPointList();
    }

}
