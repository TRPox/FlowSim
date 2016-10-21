package irmb.flowsim.presentation.factories;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.PolyLine;
import irmb.flowsim.model.geometry.Rectangle;
import irmb.flowsim.model.geometry.Shape;

/**
 * Created by Sven on 20.10.2016.
 */
public class ShapeFactoryImpl implements ShapeFactory {
    @Override
    public Shape makeShape(String type) {
        switch (type) {
            case "Line":
                return new Line();
            case "Rectangle":
                return new Rectangle();
            case "PolyLine":
                return new PolyLine();
            default:
                return null;
        }

    }
}
