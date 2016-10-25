package irmb.flowsim.view;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.PolyLine;
import irmb.flowsim.model.geometry.Rectangle;
import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.presentation.GraphicView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sven on 24.10.2016.
 */
public class GraphicViewImpl extends JPanel implements GraphicView {

    private Shape shape;

    @Override
    public void receiveShape(Shape shape) {
        this.shape = shape;
        paintComponent(getGraphics());
    }

    @Override
    public void update() {

    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        shape.paint(graphics);
    }
}
