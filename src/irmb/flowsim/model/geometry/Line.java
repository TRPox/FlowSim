package irmb.flowsim.model.geometry;

import java.awt.*;

/**
 * Created by Sven on 20.10.2016.
 */
public class Line implements Shape {
    private Point start;
    private Point end;

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    @Override
    public void paint(Graphics graphics) {

    }
}
