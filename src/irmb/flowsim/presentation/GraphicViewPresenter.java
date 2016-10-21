package irmb.flowsim.presentation;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.PolyLine;
import irmb.flowsim.model.geometry.Rectangle;
import irmb.flowsim.presentation.builders.ShapeBuilder;
import irmb.flowsim.presentation.factories.ShapeBuilderFactory;
import irmb.flowsim.presentation.factories.ShapeBuilderFactoryImpl;

import java.util.List;

/**
 * Created by Sven on 19.10.2016.
 */
public class GraphicViewPresenter {
    private GraphicView graphicView;
    private String objectType = "";
    private int timesClicked;
    private Point lastPoint;
    private List<Point> pointList;
    private ShapeBuilder shapeBuilder;

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
        this.objectType = objectType;
        ShapeBuilderFactory factory = new ShapeBuilderFactoryImpl();
        shapeBuilder = factory.makeShapeBuilder(objectType);
    }
}
