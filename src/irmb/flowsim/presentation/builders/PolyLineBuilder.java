package irmb.flowsim.presentation.builders;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.PolyLine;
import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.presentation.factories.ShapeFactory;

/**
 * Created by Sven on 20.10.2016.
 */
public class PolyLineBuilder extends ShapeBuilder {

    private PolyLine polyLine;

    public PolyLineBuilder(ShapeFactory shapeFactory) {
        super(shapeFactory);
        polyLine = (PolyLine) shapeFactory.makeShape("PolyLine");
    }

    @Override
    public void addPoint(Point point) {
        polyLine.addPoint(point);
    }

    @Override
    public Shape getShape() {
        return polyLine;
    }
}
