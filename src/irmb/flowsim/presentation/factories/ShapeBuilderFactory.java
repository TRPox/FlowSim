package irmb.flowsim.presentation.factories;

import irmb.flowsim.presentation.builders.GraphicShapeBuilder;

/**
 * Created by Sven on 20.10.2016.
 */
public interface ShapeBuilderFactory {
    GraphicShapeBuilder makeShapeBuilder(String type);
}
