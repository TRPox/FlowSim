package irmb.flowsim.presentation.builders;

import irmb.flowsim.model.geometry.PolyLine;
import irmb.flowsim.view.GraphicPolyLine;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.view.GraphicShape;
import irmb.flowsim.presentation.factories.ShapeFactory;

/**
 * Created by Sven on 20.10.2016.
 */
public class GraphicPolyLineBuilder extends GraphicShapeBuilder {

    private GraphicPolyLine graphicPolyLine;
    private PolyLine polyLine;

    public GraphicPolyLineBuilder(ShapeFactory shapeFactory) {
        super(shapeFactory);
        polyLine = (PolyLine) shapeFactory.makeShape("PolyLine");
        graphicPolyLine = new GraphicPolyLine(polyLine);
    }

    @Override
    public void addPoint(Point point) {
        polyLine.addPoint(point);
    }

    @Override
    public GraphicShape getShape() {
        return graphicPolyLine;
    }
}
