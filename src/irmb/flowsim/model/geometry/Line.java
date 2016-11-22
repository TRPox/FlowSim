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
    public boolean isPointOnBoundary(Point point) {
        if (isInBoundingBox(point))
            return true;
        return false;
    }

    private boolean isInBoundingBox(Point point) {
        return isInXInterval(point) && isInYInterval(point);
    }

    private boolean isInYInterval(Point point) {
        return point.getY() >= start.getY() && point.getY() <= end.getY();
    }

    private boolean isInXInterval(Point point) {
        return point.getX() >= start.getX() && point.getX() <= end.getX();
    }
}
