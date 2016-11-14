package irmb.test.presentation.factories;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.PolyLine;
import irmb.flowsim.model.geometry.Rectangle;
import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.presentation.factories.GraphicShapeFactory;
import irmb.flowsim.view.GraphicShape;
import irmb.test.view.GraphicLineSpy;
import irmb.test.view.GraphicPolyLineSpy;
import irmb.test.view.GraphicRectangleSpy;

/**
 * Created by Sven on 20.10.2016.
 */
public class GraphicShapeFactoryStub implements GraphicShapeFactory {
    @Override
    public GraphicShape makeShape(Shape shape) {
        if (shape instanceof Line)
            return new GraphicLineSpy((Line) shape);
        else if (shape instanceof Rectangle)
            return new GraphicRectangleSpy((Rectangle) shape);
        else if (shape instanceof PolyLine)
            return new GraphicPolyLineSpy((PolyLine) shape);
        else
            return null;

    }
}
