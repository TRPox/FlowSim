package irmb.flowsim.model.geometry;

/**
 * Created by Sven on 25.10.2016.
 */
public class Line extends Shape {
    private Point start;
    private Point end;
    private Point minPoint;
    private Point maxPoint;

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
        notifyObservers();
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
        notifyObservers();
    }

    @Override
    public boolean isPointOnBoundary(Point point, double radius) {
        maxPoint = start.getX() < end.getX() ? end : start;
        minPoint = start.getX() < end.getX() ? start : end;

        if (point.getX() > maxPoint.getX())
            return getDistance(point, maxPoint) <= radius;

        Point intersection = getIntersectionPoint(point);
        double dist = getDistance(point, intersection);
        return dist <= radius;
    }

    private Point getIntersectionPoint(Point point) {
        double gradient = getGradient();
        double reverseGradient = -1. / gradient;
        double YIntercept = start.getY() - gradient * start.getX();
        double reverseYIntercept = point.getY() - reverseGradient * point.getX();
        double intersectX = (reverseYIntercept - YIntercept) / (gradient + 1 / gradient);
        double intersectY = getYCoord(intersectX);
        return new Point((int) intersectX, (int) intersectY);
    }

    private double getDistance(Point point, Point pointOnLine) {
        double dx = Math.abs(point.getX() - pointOnLine.getX());
        double dy = Math.abs(point.getY() - pointOnLine.getY());
        return Math.sqrt(dx * dx + dy * dy);
    }

    private double getYCoord(double x) {
        double gradient = getGradient();
        double dx = getDeltaXToLineStart(x);
        return minPoint.getY() + dx * gradient;
    }

    private double getDeltaXToLineStart(double x) {
        return Math.abs((double) minPoint.getX() - x);
    }

    private double getGradient() {
        return ((double) end.getY() - (double) start.getY()) /
                ((double) end.getX() - (double) start.getX());
    }
}
