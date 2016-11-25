package irmb.flowsim.model.geometry;

/**
 * Created by Sven on 25.10.2016.
 */
public class Rectangle extends Shape {

    private Point first;
    private Point second;

    public void setFirst(Point first) {
        this.first = first;
        notifyObservers();
    }

    public Point getFirst() {
        return first;
    }

    public Point getSecond() {
        return second;
    }

    public void setSecond(Point second) {
        this.second = second;
        notifyObservers();
    }

    @Override
    public boolean isPointOnBoundary(Point point, double radius) {
        return isInBoundingBox(point) && !isInside(point);
    }

    private boolean isInside(Point point) {
        return point.getY() > first.getY() && point.getY() < second.getY() && point.getX() != first.getX() && point.getX() != second.getX();
    }

    private boolean isInBoundingBox(Point point) {
        return isInXBounds(point) && isInYBounds(point);
    }

    private boolean isInYBounds(Point point) {
        return point.getY() >= first.getY() && point.getY() <= second.getY();
    }

    private boolean isInXBounds(Point point) {
        return point.getX() >= first.getX() && point.getX() <= second.getX();
    }
}
