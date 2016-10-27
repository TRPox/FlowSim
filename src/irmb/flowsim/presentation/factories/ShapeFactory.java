package irmb.flowsim.presentation.factories;

import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.view.GraphicShape;

/**
 * Created by Sven on 20.10.2016.
 */
public interface ShapeFactory {
    Shape makeShape(String type);
}
