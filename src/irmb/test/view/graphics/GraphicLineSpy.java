package irmb.test.view.graphics;

import irmb.flowsim.model.geometry.*;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.view.graphics.GraphicLine;

import java.awt.*;


/**
 * Created by Sven on 24.10.2016.
 */
public class GraphicLineSpy extends GraphicLine {
    private boolean wasPainted;
    private int timesPainted;

    public GraphicLineSpy(Line line) {
        super(line);
    }

    public Point getStart() {
        return ((Line) shape).getStart();
    }

    public Point getEnd() {
        return ((Line) shape).getEnd();
    }

    @Override
    public void paint(Graphics graphics) {
        wasPainted = true;
        timesPainted++;
    }

    public boolean wasPainted() {
        return wasPainted;
    }

    public int getTimesPainted() {
        return timesPainted;
    }
}
