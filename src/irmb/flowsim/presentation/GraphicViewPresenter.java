package irmb.flowsim.presentation;

import irmb.flowsim.command.BuildShapeCommand;
import irmb.flowsim.command.MoveShapeCommand;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.builders.GraphicShapeBuilder;
import irmb.flowsim.presentation.factories.GraphicShapeBuilderFactory;
import irmb.flowsim.view.graphics.GraphicShape;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sven on 19.10.2016.
 */
public class GraphicViewPresenter {
    private GraphicView graphicView;
    private GraphicShapeBuilder shapeBuilder;
    private final GraphicShapeBuilderFactory factory;

    private int pointsAdded;

    private Point clickedPoint;
    private GraphicShape graphicShape;

    private GraphicShapeRepository repository = new GraphicShapeRepository();

    private Point origin;

    private CommandQueue commandQueue = new CommandQueue();
    private MoveShapeCommand moveShapeCommand;

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
        } else
            createMoveObjectCommand(x, y);
    }

    private void createMoveObjectCommand(double x, double y) {
        clickedPoint = makePoint(x, y);
        graphicShape = repository.getGraphicShapeAt(clickedPoint);
        if (graphicShape != null) {
            origin = makePoint(x, y);
            moveShapeCommand = new MoveShapeCommand(graphicShape.getShape());
            commandQueue.add(moveShapeCommand);
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
        BuildShapeCommand buildShapeCommand = new BuildShapeCommand(shapeBuilder.getShape(), graphicView, repository);
        buildShapeCommand.execute();
        commandQueue.add(buildShapeCommand);
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

        moveShapeCommand.setDeltaX(dx);
        moveShapeCommand.setDeltaY(dy);
        moveShapeCommand.execute();

        clickedPoint.setX(x);
        clickedPoint.setY(y);
    }

    private boolean onePointWasAdded() {
        return pointsAdded == 1;
    }

    private void setLastPointTo(double x, double y) {
        shapeBuilder.setLastPoint(makePoint(x, y));
    }

    public void undo() {
        commandQueue.undo();
    }

    public void redo() {
        commandQueue.redo();
    }

    public void handleMouseRelease() {
        graphicShape = null;
        if (moveShapeCommand != null) {
            double dx = clickedPoint.getX() - origin.getX();
            double dy = clickedPoint.getY() - origin.getY();
            moveShapeCommand.setDeltaX(dx);
            moveShapeCommand.setDeltaY(dy);
        }
    }

    public void setRepository(GraphicShapeRepository repository) {
        this.repository = repository;
    }
}
