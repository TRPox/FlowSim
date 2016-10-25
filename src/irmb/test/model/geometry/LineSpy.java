package irmb.test.model.geometry;

import irmb.flowsim.model.geometry.Line;

import java.awt.*;

/**
 * Created by Sven on 24.10.2016.
 */
public class LineSpy extends Line {
    private boolean wasPainted;
    private int timesPainted;

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
