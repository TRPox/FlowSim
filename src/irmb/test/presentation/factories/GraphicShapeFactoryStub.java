package irmb.test.presentation.factories;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.PolyLine;
import irmb.flowsim.model.geometry.Rectangle;
import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.presentation.factories.GraphicShapeFactory;
import irmb.flowsim.view.GraphicPolyLine;
import irmb.flowsim.view.GraphicRectangle;
import irmb.flowsim.view.GraphicShape;
import irmb.test.model.geometry.GraphicLineSpy;
import irmb.test.model.geometry.GraphicPolyLineSpy;
import irmb.test.model.geometry.GraphicRectangleSpy;

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
