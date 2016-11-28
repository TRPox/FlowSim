package irmb.flowsim.model.geometry;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sven on 25.10.2016.
 */
public class PolyLine extends Shape {

    private List<Point> pointList = new LinkedList<>();
    private Point maxPoint;
    private Point minPoint;
    private double segmentGradient;

    public void addPoint(Point point) {
        pointList.add(point);
        notifyObservers();
    }

    public List<Point> getPointList() {
        return new ArrayList<>(pointList);
    }

    public void setLastPoint(Point point) {
        pointList.set(pointList.size() - 1, point);
        notifyObservers();
    }

    public void removeLastPoint() {
        if (!pointList.isEmpty())
            pointList.remove(pointList.size() - 1);
        notifyObservers();
    }

    @Override
    public void moveBy(double dx, double dy) {
        for (Point point : pointList) {
            point.setX(point.getX() + dx);
            point.setY(point.getY() + dy);
        }
        notifyObservers();
    }

    @Override
    public boolean isPointOnBoundary(Point point, double radius) {
        for (int i = 0; i < pointList.size() - 1; i++) {
            maxPoint = pointList.get(i).getX() < pointList.get(i + 1).getX() ? pointList.get(i + 1) : pointList.get(i);
            minPoint = pointList.get(i).getX() < pointList.get(i + 1).getX() ? pointList.get(i) : pointList.get(i + 1);

            segmentGradient = getGradient(i);

            Point intersection = getIntersectionPoint(point, i);
            double dist = getDistance(point, intersection);
            if (dist <= radius)
                return true;
        }
        return false;
    }

    private Point getIntersectionPoint(Point point, int i) {
        double reverseGradient = -1. / segmentGradient;
        double YIntercept = pointList.get(i).getY() - segmentGradient * pointList.get(i).getX();
        double reverseYIntercept = point.getY() - reverseGradient * point.getX();
        double intersectX = (reverseYIntercept - YIntercept) / (segmentGradient + 1 / segmentGradient);
        double intersectY = getYCoord(intersectX);
        return new Point(intersectX, intersectY);
    }

    private double getDistance(Point point, Point pointOnLine) {
        double dx = Math.abs(point.getX() - pointOnLine.getX());
        double dy = Math.abs(point.getY() - pointOnLine.getY());
        return Math.sqrt(dx * dx + dy * dy);
    }

    private double getYCoord(double x) {
        double dx = getDeltaXToLineStart(x);
        return minPoint.getY() + dx * segmentGradient;
    }

    private double getDeltaXToLineStart(double x) {
        return Math.abs(minPoint.getX() - x);
    }

    private double getGradient(int i) {
        return (pointList.get(i + 1).getY() - pointList.get(i).getY()) /
                (pointList.get(i + 1).getX() - pointList.get(i).getX());
    }
}
