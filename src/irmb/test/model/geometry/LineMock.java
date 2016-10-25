package irmb.test.model.geometry;

import irmb.flowsim.model.geometry.Line;

import java.awt.*;

/**
 * Created by Sven on 24.10.2016.
 */
public class LineMock extends Line {
    private boolean wasPainted;

    @Override
    public void paint(Graphics graphics) {
        wasPainted = true;
    }

    public boolean wasPainted() {
        return wasPainted;
    }
}
