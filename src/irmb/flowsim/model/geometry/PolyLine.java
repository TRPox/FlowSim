package irmb.flowsim.model.geometry;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sven on 25.10.2016.
 */
public class PolyLine extends Shape {

    private List<Point> pointList = new LinkedList<>();

    public void addPoint(Point point) {
        pointList.add(point);
        notifyObservers();
    }

    public List<Point> getPointList() {
        return pointList;
    }
}
