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
        PolyLine polyLine = (PolyLine) shape;
        List<Point> pointList = polyLine.getPointList();
        for (int i = 0; i < pointList.size() - 1; i++) {
            Point start = pointList.get(i);
            Point end = pointList.get(i + 1);
            graphics.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
        }
    }

    @Override
    public void update() {
        notifyObservers();
    }
}
