package irmb.flowsim.presentation.builders;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.Rectangle;
import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.presentation.factories.ShapeFactory;

/**
 * Created by Sven on 20.10.2016.
 */
public class RectangleBuilder extends ShapeBuilder {

    private Rectangle rectangle;
    private int pointsAdded;

    public RectangleBuilder(ShapeFactory shapeFactory) {
        super(shapeFactory);
        rectangle = (Rectangle) shapeFactory.makeShape("Rectangle");
    }

    @Override
    public void addPoint(Point point) {
        pointsAdded++;
        if (pointsAdded == 1)
            rectangle.setFirst(point);
        else if (pointsAdded == 2)
            rectangle.setSecond(point);
    }

    @Override
    public Shape getShape() {
        return rectangle;
    }
}
