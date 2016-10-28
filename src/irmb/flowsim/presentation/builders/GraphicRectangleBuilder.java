package irmb.flowsim.presentation.builders;

import irmb.flowsim.model.geometry.Rectangle;
import irmb.flowsim.view.GraphicRectangle;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.view.GraphicShape;
import irmb.flowsim.presentation.factories.ShapeFactory;

/**
 * Created by Sven on 20.10.2016.
 */
public class GraphicRectangleBuilder extends GraphicShapeBuilder {

    private GraphicRectangle graphicRectangle;
    private Rectangle rectangle;
    private int pointsAdded;

    public GraphicRectangleBuilder(ShapeFactory shapeFactory) {
        super(shapeFactory);
        rectangle = (Rectangle) shapeFactory.makeShape("Rectangle");
        graphicRectangle = new GraphicRectangle(rectangle);
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
    public GraphicShape getShape() {
        return graphicRectangle;
    }
}
