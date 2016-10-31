package irmb.test.model.geometry;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.PolyLine;
import irmb.flowsim.view.GraphicLine;
import irmb.flowsim.view.GraphicPolyLine;
import irmb.flowsim.view.GraphicShape;

import java.awt.*;
import java.util.List;

/**
 * Created by Sven on 31.10.2016.
 */
public class GraphicPolyLineSpy extends GraphicPolyLine {

    public GraphicPolyLineSpy(PolyLine shape) {
        super(shape);
    }

    public List<Point> getPointList() {
        return ((PolyLine) shape).getPointList();
    }

}
