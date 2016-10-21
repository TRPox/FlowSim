package irmb.flowsim.presentation.builders;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.presentation.factories.ShapeFactory;

/**
 * Created by Sven on 20.10.2016.
 */
public class LineBuilder extends ShapeBuilder {

    private Line line;
    private int pointsAdded;

    public LineBuilder(ShapeFactory shapeFactory) {
        super(shapeFactory);
        line = (Line) shapeFactory.makeShape("Line");
    }

    @Override
    public void addPoint(Point point) {
        pointsAdded++;
        if (pointsAdded == 1)
            line.setStart(point);
        else if (pointsAdded == 2)
            line.setEnd(point);
    }

    @Override
    public Shape getShape() {
        return line;
    }
}
