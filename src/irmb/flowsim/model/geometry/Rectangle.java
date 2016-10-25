package irmb.flowsim.model.geometry;

import irmb.flowsim.presentation.GraphicView;

import java.awt.*;

/**
 * Created by Sven on 20.10.2016.
 */
public class Rectangle implements Shape {
    private Point first;
    private Point second;
    private GraphicView view;

    public Point getFirst() {
        return first;
    }

    public void setFirst(Point first) {
        this.first = first;
        notifyView();
    }

    private void notifyView() {
        if (view != null) view.update();
    }

    public Point getSecond() {
        return second;
    }

    public void setSecond(Point second) {
        this.second = second;
        notifyView();
    }

    @Override
    public void paint(Graphics graphics) {

    }

    public void addGraphicView(GraphicView view) {
        this.view = view;
    }
}
