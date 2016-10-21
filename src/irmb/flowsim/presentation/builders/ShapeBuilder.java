package irmb.flowsim.presentation.builders;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.presentation.factories.ShapeFactory;

/**
 * Created by Sven on 20.10.2016.
 */
public abstract class ShapeBuilder {

    protected ShapeFactory shapeFactory;

    public ShapeBuilder(ShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

    public abstract void addPoint(Point point);

    public abstract Shape getShape();

}
