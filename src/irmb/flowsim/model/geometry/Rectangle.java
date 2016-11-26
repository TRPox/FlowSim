package irmb.flowsim.model.geometry;

/**
 * Created by Sven on 25.10.2016.
 */
public class Rectangle extends Shape {

    private Point first;
    private Point second;
    private int maxX;
    private int minX;
    private int maxY;
    private int minY;

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
    public void moveBy(int dx, int dy) {
        first.setX(first.getX() + dx);
        first.setY(first.getY() + dy);
        second.setX(second.getX() + dx);
        second.setY(second.getY() + dy);
    }

    @Override
    public boolean isPointOnBoundary(Point point, double radius) {
        if (first.getX() < second.getX()) {
            maxX = second.getX();
            minX = first.getX();
        } else {
            maxX = first.getX();
            minX = second.getX();
        }
        if (first.getY() < second.getY()) {
            maxY = second.getY();
            minY = first.getY();
        } else {
            maxY = first.getY();
            minY = second.getY();
        }
        return isInBoundingBox(point, radius) && !isInside(point, radius);
    }

    private boolean isInside(Point point, double radius) {
        return point.getY() > minY + radius && point.getY() < maxY - radius && point.getX() > minX + radius && point.getX() < maxX - radius;
    }

    private boolean isInBoundingBox(Point point, double radius) {
        return isInXBounds(point, radius) && isInYBounds(point, radius);
    }

    private boolean isInYBounds(Point point, double radius) {
        return point.getY() >= minY - radius && point.getY() <= maxY + radius;
    }

    private boolean isInXBounds(Point point, double radius) {
        return point.getX() >= minX - radius && point.getX() <= maxX + radius;
    }
}
