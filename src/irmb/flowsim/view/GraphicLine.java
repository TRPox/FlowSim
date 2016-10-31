package irmb.flowsim.view;

import java.awt.*;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.Point;

/**
 * Created by Sven on 20.10.2016.
 */
public class GraphicLine extends GraphicShape {


    public GraphicLine(Line line) {
        this.shape = line;
        shape.addObserver(this);
    }



    @Override
    public void paint(Graphics graphics) {
        Line line = (Line) shape;
        Point start = line.getStart();
        Point end = line.getEnd();
        graphics.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
    }

    @Override
    public void update() {
        notifyObservers();
    }
}
