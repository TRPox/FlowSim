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

    public Point getStart() {
        return ((Line) shape).getStart();
    }

    public Point getEnd() {
        return ((Line) shape).getEnd();
    }

    @Override
    public void paint(Graphics graphics) {

    }

    @Override
    public void update() {
        notifyObservers();
    }
}