package irmb.flowsim.presentation;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.builders.GraphicShapeBuilder;
import irmb.flowsim.presentation.factories.ShapeBuilderFactory;
import irmb.flowsim.presentation.factories.ShapeBuilderFactoryImpl;

/**
 * Created by Sven on 19.10.2016.
 */
public class GraphicViewPresenter {
    private GraphicView graphicView;
    private int timesClicked;
    private GraphicShapeBuilder shapeBuilder;

    public void handleLeftClick(int x, int y) {
        timesClicked++;
        if (shapeBuilder != null) {
            shapeBuilder.addPoint(new Point(x, y));
            if (timesClicked == 2) {
                graphicView.receiveShape(shapeBuilder.getShape());
            }
        }
    }

    public void setGraphicView(GraphicView view) {
        this.graphicView = view;
    }

    public void setObjectType(String objectType) {
        ShapeBuilderFactory factory = new ShapeBuilderFactoryImpl();
        shapeBuilder = factory.makeShapeBuilder(objectType);
        timesClicked = 0;
    }

    public void handleRightClick(int x, int y) {
        shapeBuilder = null;
    }
}
