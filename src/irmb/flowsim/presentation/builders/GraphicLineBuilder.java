package irmb.flowsim.presentation.builders;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.view.GraphicLine;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.view.GraphicShape;
import irmb.flowsim.presentation.factories.GraphicShapeFactory;

/**
 * Created by Sven on 20.10.2016.
 */
public class GraphicLineBuilder extends GraphicShapeBuilder {

    private GraphicLine graphicLine;
    private Line line;
    private int pointsAdded;

    public GraphicLineBuilder(GraphicShapeFactory shapeFactory) {
        super(shapeFactory);
        line = new Line();
        graphicLine = (GraphicLine) shapeFactory.makeShape(line);
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
    public GraphicShape getShape() {
        return graphicLine;
    }

    @Override
    public void setLastPoint(Point point) {
        if (pointsAdded == 1)
            line.setStart(point);
        else if (pointsAdded >= 2)
            line.setEnd(point);
    }

    @Override
    public boolean isObjectFinished() {
        return pointsAdded > 1;
    }
}
