package irmb.flowsim.presentation;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.view.graphics.GraphicShape;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sven on 21.11.2016.
 */
public class GraphicShapeRepository {

    private List<GraphicShape> graphicShapeList = new ArrayList<>();

    public void add(GraphicShape graphicShape) {
        graphicShapeList.add(graphicShape);
    }

    public void remove(GraphicShape graphicShape) {
        graphicShapeList.remove(graphicShape);
    }

    public void remove(int i) {
        if (graphicShapeList.size() > 0)
            graphicShapeList.remove(i);
    }

    public List<GraphicShape> getGraphicShapeList() {
        return new ArrayList<>(graphicShapeList);
    }

    public GraphicShape getGraphicShapeAt(Point point) {
        for (GraphicShape graphicShape : graphicShapeList) {
            Line line = (Line) graphicShape.getShape();
            if (isOnLine(line, point))
                return graphicShape;
        }
        return null;
    }

    private boolean isOnLine(Line line, Point point) {
        double lineYCoord = getYCoord(line, point);
        int minX, maxX;
        if (line.getStart().getX() <= line.getEnd().getX()) {
            minX = line.getStart().getX();
            maxX = line.getEnd().getX();
        } else {
            minX = line.getEnd().getX();
            maxX = line.getStart().getX();
        }

        return Math.abs(lineYCoord - point.getY()) <= 3 && point.getX() >= minX && point.getX() <= maxX;
    }

    private double getYCoord(Line line, Point point) {
        double gradient = getGradient(line);
        int dx = getDeltaXToLineStart(point, line);
        return line.getStart().getY() + dx * gradient;
    }

    private int getDeltaXToLineStart(Point point, Line line) {
        return Math.abs(line.getStart().getX() - point.getX());
    }

    private double getGradient(Line line) {
        return ((double) line.getEnd().getY() - (double) line.getStart().getY()) /
                ((double) line.getEnd().getX() - (double) line.getStart().getX());
    }


}
