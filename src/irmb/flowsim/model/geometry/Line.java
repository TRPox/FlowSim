package irmb.flowsim.model.geometry;

/**
 * Created by Sven on 25.10.2016.
 */
public class Line extends Shape {
    private Point start;
    private Point end;

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
        double lineYCoord = getYCoord(point);
        int minX, maxX;
        if (start.getX() <= end.getX()) {
            minX = start.getX();
            maxX = end.getX();
        } else {
            minX = end.getX();
            maxX = start.getX();
        }
        return Math.abs(lineYCoord - point.getY()) <= radius && point.getX() >= minX && point.getX() <= maxX;
    }

    private double getYCoord(Point point) {
        double gradient = getGradient();
        int dx = getDeltaXToLineStart(point);
        return start.getY() + dx * gradient;
    }

    private int getDeltaXToLineStart(Point point) {
        return Math.abs(start.getX() - point.getX());
    }

    private double getGradient() {
        return ((double) end.getY() - (double) start.getY()) /
                ((double) end.getX() - (double) start.getX());
    }
}
