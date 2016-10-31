package irmb.flowsim.presentation.factories;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.PolyLine;
import irmb.flowsim.model.geometry.Rectangle;
import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.view.GraphicLine;
import irmb.flowsim.view.GraphicPolyLine;
import irmb.flowsim.view.GraphicRectangle;
import irmb.flowsim.view.GraphicShape;

/**
 * Created by Sven on 20.10.2016.
 */
public class GraphicShapeFactoryImpl implements GraphicShapeFactory {
    @Override
    public GraphicShape makeShape(Shape shape) {
        if (shape instanceof Line)
            return new GraphicLine((Line) shape);
        else if (shape instanceof Rectangle)
            return new GraphicRectangle((Rectangle) shape);
        else if (shape instanceof PolyLine)
            return new GraphicPolyLine((PolyLine) shape);
        else
            return null;
    }
}
