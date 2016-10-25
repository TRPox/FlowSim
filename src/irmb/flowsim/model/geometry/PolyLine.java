package irmb.flowsim.model.geometry;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sven on 20.10.2016.
 */
public class PolyLine implements Shape {
    private List<Point> pointList = new LinkedList<>();

    public List<Point> getPointList() {
        return pointList;
    }

    public void setPointList(List<Point> pointList) {
        this.pointList = pointList;
    }

    @Override
    public void paint(Graphics graphics) {

    }
}
