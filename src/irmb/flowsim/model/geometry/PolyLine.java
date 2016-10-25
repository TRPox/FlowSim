package irmb.flowsim.model.geometry;

import irmb.flowsim.presentation.GraphicView;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sven on 20.10.2016.
 */
public class PolyLine extends Shape {
    private List<Point> pointList = new LinkedList<>();

    public void addPoint(Point point) {
        pointList.add(point);
        notifyView();
    }

    public List<Point> getPointList() {
        return pointList;
    }

    @Override
    public void paint(Graphics graphics) {

    }
}
