package irmb.flowsim.presentation.builders;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.Rectangle;
import irmb.flowsim.presentation.factories.GraphicShapeFactory;
import irmb.flowsim.view.graphics.GraphicRectangle;
import irmb.flowsim.view.graphics.GraphicShape;

/**
 * Created by Sven on 20.10.2016.
 */
public class GraphicRectangleBuilder extends GraphicShapeBuilder {

    private GraphicRectangle graphicRectangle;
    private Rectangle rectangle;
    private int pointsAdded;

    public GraphicRectangleBuilder(GraphicShapeFactory shapeFactory) {
        super(shapeFactory);
        rectangle = new Rectangle();
        graphicRectangle = (GraphicRectangle) shapeFactory.makeShape(rectangle);
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

    @Override
    public void setLastPoint(Point point) {
        if (pointsAdded == 1)
            rectangle.setFirst(point);
        else if (pointsAdded >= 2)
            rectangle.setSecond(point);
    }

    @Override
    public void removeLastPoint() {

    }

    @Override
    public boolean isObjectFinished() {
        return pointsAdded > 1;
    }
}
