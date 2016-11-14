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
}
