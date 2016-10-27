package irmb.flowsim.view;

import java.awt.*;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.Rectangle;

/**
 * Created by Sven on 20.10.2016.
 */
public class GraphicRectangle extends GraphicShape {


    public GraphicRectangle(Rectangle rectangle) {
        this.shape = rectangle;
        shape.addObserver(this);
    }

    public Point getFirst() {
        return ((Rectangle) shape).getFirst();
    }

    public Point getSecond() {
        return ((Rectangle) shape).getSecond();
    }

    @Override
    public void paint(Graphics graphics) {
        Rectangle rectangle = (Rectangle) shape;
        Point first = rectangle.getFirst();
        Point second = rectangle.getSecond();
        graphics.drawRect(first.getX(), first.getY(), second.getX(), second.getY());
    }

    @Override
    public void update() {
        notifyObservers();
    }
}
