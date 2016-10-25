package irmb.flowsim.model.geometry;

import java.awt.*;

/**
 * Created by Sven on 20.10.2016.
 */
public class Rectangle implements Shape {
    private Point first;
    private Point second;

    public Point getFirst() {
        return first;
    }

    public void setFirst(Point first) {
        this.first = first;
    }

    public Point getSecond() {
        return second;
    }

    public void setSecond(Point second) {
        this.second = second;
    }

    @Override
    public void paint(Graphics graphics) {
        
    }
}
