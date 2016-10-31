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



    @Override
    public void paint(Graphics graphics) {
        Rectangle rectangle = (Rectangle) shape;
        Point first = rectangle.getFirst();
        Point second = rectangle.getSecond();
        int minX = first.getX() < second.getX() ? first.getX() : second.getX();
        int minY = first.getY() < second.getY() ? first.getY() : second.getY();
        int width = Math.abs(first.getX() - second.getX());
        int height = Math.abs(first.getY() - second.getY());
        graphics.drawRect(minX, minY, width, height);
    }

    @Override
    public void update() {
        notifyObservers();
    }
}
