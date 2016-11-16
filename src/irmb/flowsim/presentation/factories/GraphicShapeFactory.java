package irmb.flowsim.presentation.factories;

import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.view.graphics.GraphicShape;

/**
 * Created by Sven on 20.10.2016.
 */
public interface GraphicShapeFactory {
    GraphicShape makeShape(Shape shape);
}
