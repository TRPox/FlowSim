package irmb.flowsim.presentation;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.builders.GraphicShapeBuilder;
import irmb.flowsim.presentation.factories.GraphicShapeBuilderFactory;
import irmb.flowsim.view.graphics.GraphicShape;

/**
 * Created by Sven on 19.10.2016.
 */
public class GraphicViewPresenter {
    private GraphicView graphicView;
    private GraphicShapeBuilder shapeBuilder;
    private final GraphicShapeBuilderFactory factory;

    private int pointsAdded;
    private int currentIndex = -1;

    private Point clickedPoint;
    private GraphicShape graphicShape;

    private GraphicShapeRepository repository = new GraphicShapeRepository();

    private GraphicShape lastMovedShape;
    private Point origin;


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

    public void handleLeftClick(double x, double y) {
        if (hasShapeBuilder()) {
            addPointToShape(x, y);
            if (twoPointsWereAdded())
                sendShapeToView();
            if (shapeBuilder.isObjectFinished())
                shapeBuilder = null;
        } else {
            clickedPoint = makePoint(x, y);
            origin = makePoint(x, y);
            graphicShape = repository.getGraphicShapeAt(clickedPoint);

        }
    }

    private boolean hasShapeBuilder() {
        return shapeBuilder != null;
    }

    private boolean twoPointsWereAdded() {
        return pointsAdded == 2;
    }

    private void addPointToShape(double x, double y) {
        shapeBuilder.addPoint(makePoint(x, y));
        pointsAdded++;
    }

    private Point makePoint(double x, double y) {
        return new Point(x, y);
    }

    private void sendShapeToView() {
        graphicView.receiveShape(shapeBuilder.getShape());
        for (int i = ++currentIndex; i < repository.getGraphicShapeList().size(); i++)
            repository.remove(i);
        repository.add(shapeBuilder.getShape());
    }

    public void handleRightClick(double x, double y) {
        if (hasShapeBuilder()) {
            if (pointsAdded <= 2)
                graphicView.removeShape(shapeBuilder.getShape());
            else
                shapeBuilder.removeLastPoint();
        }
        shapeBuilder = null;
    }

    public void handleMouseMove(double x, double y) {
        if (hasShapeBuilder())
            if (onePointWasAdded()) {
                addPointToShape(x, y);
                sendShapeToView();
            } else if (pointsAdded > 1) {
                setLastPointTo(x, y);
            }
    }

    public void handleMouseDrag(double x, double y) {
        if (graphicShape != null) {
            moveShape(x, y);
        }
    }

    private void moveShape(double x, double y) {
        double dx = x - clickedPoint.getX();
        double dy = y - clickedPoint.getY();

        graphicShape.getShape().moveBy(dx, dy);

        clickedPoint.setX(x);
        clickedPoint.setY(y);

        lastMovedShape = graphicShape;
    }

    private boolean onePointWasAdded() {
        return pointsAdded == 1;
    }

    private void setLastPointTo(double x, double y) {
        shapeBuilder.setLastPoint(makePoint(x, y));
    }

    public void undo() {
        if (lastMovedShape == null) {
            if (currentIndex > -1) {
                graphicView.removeShape(repository.getGraphicShapeList().get(currentIndex));
                currentIndex--;
            }
        } else {
            double dx = clickedPoint.getX() - origin.getX();
            double dy = clickedPoint.getY() - origin.getY();
            lastMovedShape.getShape().moveBy(-dx, -dy);
            lastMovedShape = null;
        }
    }

    public void redo() {
        if (currentIndex + 1 < repository.getGraphicShapeList().size())
            graphicView.receiveShape(repository.getGraphicShapeList().get(++currentIndex));
    }


    public void handleMouseRelease() {
        graphicShape = null;
    }

    public void setRepository(GraphicShapeRepository repository) {
        this.repository = repository;
    }
}
