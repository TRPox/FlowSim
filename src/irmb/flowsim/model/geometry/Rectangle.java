package irmb.flowsim.model.geometry;

import irmb.flowsim.presentation.GraphicView;

import java.awt.*;

/**
 * Created by Sven on 20.10.2016.
 */
public class Rectangle extends Shape {
    private Point first;
    private Point second;

    public Point getFirst() {
        return first;
    }

    public void setFirst(Point first) {
        this.first = first;
        notifyView();
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
}
