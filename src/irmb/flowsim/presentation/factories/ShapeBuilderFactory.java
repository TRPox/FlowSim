package irmb.flowsim.presentation.factories;

import irmb.flowsim.presentation.builders.ShapeBuilder;

/**
 * Created by Sven on 20.10.2016.
 */
public interface ShapeBuilderFactory {
    ShapeBuilder makeShapeBuilder(String type);
}
