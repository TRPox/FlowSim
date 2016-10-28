package irmb.flowsim.presentation.factories;

import irmb.flowsim.presentation.builders.GraphicLineBuilder;
import irmb.flowsim.presentation.builders.GraphicPolyLineBuilder;
import irmb.flowsim.presentation.builders.GraphicRectangleBuilder;
import irmb.flowsim.presentation.builders.GraphicShapeBuilder;

/**
 * Created by Sven on 20.10.2016.
 */
public class ShapeBuilderFactoryImpl implements ShapeBuilderFactory {

    private ShapeFactory factory = new ShapeFactoryImpl();

    @Override
    public GraphicShapeBuilder makeShapeBuilder(String type) {
        switch (type) {
            case "Line":
                return new GraphicLineBuilder(factory);
            case "Rectangle":
                return new GraphicRectangleBuilder(factory);
            case "PolyLine":
                return new GraphicPolyLineBuilder(factory);
            default:
                return null;
        }
    }
}
