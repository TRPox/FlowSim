package irmb.flowsim.presentation.builders;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.view.GraphicShape;
import irmb.flowsim.presentation.factories.GraphicShapeFactory;

/**
 * Created by Sven on 20.10.2016.
 */
public abstract class GraphicShapeBuilder {

    protected GraphicShapeFactory shapeFactory;

    public GraphicShapeBuilder(GraphicShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

    public abstract void addPoint(Point point);

    public abstract GraphicShape getShape();

    public abstract void setLastPoint(Point point);
}
