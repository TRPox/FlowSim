package irmb.test.view;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.Rectangle;
import irmb.flowsim.view.GraphicRectangle;

import java.awt.*;


/**
 * Created by Sven on 31.10.2016.
 */
public class GraphicRectangleSpy extends GraphicRectangle {
    public GraphicRectangleSpy(Rectangle shape) {
        super(shape);
    }

    public Point getFirst() {
        return ((Rectangle) shape).getFirst();
    }

    public Point getSecond() {
        return ((Rectangle) shape).getSecond();
    }
}
