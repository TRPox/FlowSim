package irmb.flowsim.presentation;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.builders.GraphicShapeBuilder;
import irmb.flowsim.presentation.factories.GraphicShapeBuilderFactory;
import irmb.flowsim.presentation.factories.GraphicShapeBuilderFactoryImpl;

/**
 * Created by Sven on 19.10.2016.
 */
public class GraphicViewPresenter {
    private GraphicView graphicView;
    private int timesClicked;
    private GraphicShapeBuilder shapeBuilder;
    private final GraphicShapeBuilderFactory factory;

    public GraphicViewPresenter(GraphicShapeBuilderFactory factory) {
        this.factory = factory;
    }

    public void handleLeftClick(int x, int y) {
        timesClicked++;
        if (shapeBuilder != null) {
            shapeBuilder.addPoint(makePoint(x, y));
            if (timesClicked == 2)
                graphicView.receiveShape(shapeBuilder.getShape());
            if (shapeBuilder.isObjectFinished())
                shapeBuilder = null;
        }
    }

    private Point makePoint(int x, int y) {
        return new Point(x, y);
    }

    public void setGraphicView(GraphicView view) {
        this.graphicView = view;
    }

    public void setObjectType(String objectType) {
        shapeBuilder = factory.makeShapeBuilder(objectType);
        timesClicked = 0;
    }

    public void handleRightClick(int x, int y) {
        shapeBuilder = null;
    }

    public void handleMouseMove(int x, int y) {
        if (shapeBuilder != null)
            if (timesClicked == 1) {
                shapeBuilder.addPoint(makePoint(x, y));
                graphicView.receiveShape(shapeBuilder.getShape());
                timesClicked++;
            } else if (timesClicked > 1) {
                shapeBuilder.setLastPoint(makePoint(x, y));
            }
    }
}
