package irmb.flowsim.presentation.factories;

import irmb.flowsim.presentation.builders.LineBuilder;
import irmb.flowsim.presentation.builders.PolyLineBuilder;
import irmb.flowsim.presentation.builders.RectangleBuilder;
import irmb.flowsim.presentation.builders.ShapeBuilder;

/**
 * Created by Sven on 20.10.2016.
 */
public class ShapeBuilderFactoryImpl implements ShapeBuilderFactory {

    private ShapeFactory factory = new ShapeFactoryImpl();

    @Override
    public ShapeBuilder makeShapeBuilder(String type) {
        switch (type) {
            case "GraphicLine":
                return new LineBuilder(factory);
            case "GraphicRectangle":
                return new RectangleBuilder(factory);
            case "GraphicPolyLine":
                return new PolyLineBuilder(factory);
            default:
                return null;
        }
    }
}
