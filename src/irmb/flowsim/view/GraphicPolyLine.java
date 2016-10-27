package irmb.flowsim.view;

import java.awt.*;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.PolyLine;

import java.util.List;

/**
 * Created by Sven on 20.10.2016.
 */
public class GraphicPolyLine extends GraphicShape {

    public GraphicPolyLine(PolyLine polyLine) {
        this.shape = polyLine;
        shape.addObserver(this);
    }

    public List<Point> getPointList() {
        return ((PolyLine) shape).getPointList();
    }

    @Override
    public void paint(Graphics graphics) {

    }

    @Override
    public void update() {
        notifyObservers();
    }
}
