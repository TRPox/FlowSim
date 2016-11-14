package irmb.flowsim.presentation.factories;

import irmb.flowsim.presentation.builders.GraphicShapeBuilder;

/**
 * Created by Sven on 20.10.2016.
 */
public abstract class GraphicShapeBuilderFactory {

    protected final GraphicShapeFactory factory;

    public GraphicShapeBuilderFactory(GraphicShapeFactory factory) {
        this.factory = factory;
    }

    public abstract GraphicShapeBuilder makeShapeBuilder(String type);
}
