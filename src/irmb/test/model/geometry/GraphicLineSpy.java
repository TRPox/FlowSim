package irmb.test.model.geometry;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.view.GraphicLine;

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
