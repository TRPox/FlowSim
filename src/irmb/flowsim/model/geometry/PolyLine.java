package irmb.flowsim.model.geometry;

import irmb.flowsim.presentation.GraphicView;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sven on 20.10.2016.
 */
public class PolyLine implements Shape {
    private List<Point> pointList = new LinkedList<>();
    private GraphicView view;

    public void addPoint(Point point) {
        pointList.add(point);
        notifyView();
    }

    private void notifyView() {
        if (view != null) view.update();
    }

    public List<Point> getPointList() {
        return pointList;
    }

    @Override
    public void paint(Graphics graphics) {

    }

    public void addGraphicView(GraphicView view) {
        this.view = view;
    }
}
