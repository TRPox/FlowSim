package irmb.test.presentation.factories;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.presentation.factories.ShapeFactory;

/**
 * Created by Sven on 20.10.2016.
 */
public class ShapeFactoryStub implements ShapeFactory {
    @Override
    public Shape makeShape(String type) {
        switch (type) {
            case "Line":
                return new Line();
            default:
                return null;
        }
    }
}
