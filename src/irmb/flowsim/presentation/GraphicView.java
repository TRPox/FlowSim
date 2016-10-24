package irmb.flowsim.presentation;

import irmb.flowsim.model.geometry.Shape;

/**
 * Created by Sven on 19.10.2016.
 */
public interface GraphicView {
    void receiveShape(Shape shape);
}
