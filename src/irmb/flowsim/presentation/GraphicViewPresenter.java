package irmb.flowsim.presentation;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.builders.GraphicShapeBuilder;
import irmb.flowsim.presentation.factories.GraphicShapeBuilderFactory;
import irmb.flowsim.view.graphics.GraphicShape;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sven on 19.10.2016.
 */
public class GraphicViewPresenter {
    private GraphicView graphicView;
    private int pointsAdded;
    private GraphicShapeBuilder shapeBuilder;
    private final GraphicShapeBuilderFactory factory;

    private List<GraphicShape> shapeList = new ArrayList<>();
    private int currentIndex = -1;

    public GraphicViewPresenter(GraphicShapeBuilderFactory factory) {
        this.factory = factory;
    }

    public void setObjectType(String objectType) {
        shapeBuilder = factory.makeShapeBuilder(objectType);
        pointsAdded = 0;
    }

    public void setGraphicView(GraphicView view) {
        this.graphicView = view;
    }

    public void handleLeftClick(int x, int y) {
        if (hasShapeBuilder()) {
            addPointToShape(x, y);
            if (twoPointsWereAdded())
                sendShapeToView();
            if (shapeBuilder.isObjectFinished())
                shapeBuilder = null;
        }
    }

    private boolean hasShapeBuilder() {
        return shapeBuilder != null;
    }

    private boolean twoPointsWereAdded() {
        return pointsAdded == 2;
    }

    private void addPointToShape(int x, int y) {
        shapeBuilder.addPoint(makePoint(x, y));
        pointsAdded++;
    }

    private Point makePoint(int x, int y) {
        return new Point(x, y);
    }

    private void sendShapeToView() {
        graphicView.receiveShape(shapeBuilder.getShape());
        for (int i = ++currentIndex; i < shapeList.size(); i++)
            shapeList.remove(i);
        shapeList.add(shapeBuilder.getShape());
    }

    public void handleRightClick(int x, int y) {
        if (hasShapeBuilder()) {
            if (pointsAdded <= 2)
                graphicView.removeShape(shapeBuilder.getShape());
            else
                shapeBuilder.removeLastPoint();
        }
        shapeBuilder = null;
    }

    public void handleMouseMove(int x, int y) {
        if (hasShapeBuilder())
            if (onePointWasAdded()) {
                addPointToShape(x, y);
                sendShapeToView();
            } else if (pointsAdded > 1) {
                setLastPointTo(x, y);
            }
    }

    public void handleMouseDrag(int x, int y) {

    }

    private boolean onePointWasAdded() {
        return pointsAdded == 1;
    }

    private void setLastPointTo(int x, int y) {
        shapeBuilder.setLastPoint(makePoint(x, y));
    }

    public void undo() {
        if (currentIndex > -1) {
            graphicView.removeShape(shapeList.get(currentIndex));
            currentIndex--;
        }
    }

    public void redo() {
        if (currentIndex + 1 < shapeList.size())
            graphicView.receiveShape(shapeList.get(++currentIndex));
    }


}
