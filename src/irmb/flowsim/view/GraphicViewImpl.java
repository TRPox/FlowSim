package irmb.flowsim.view;

import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.presentation.GraphicView;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sven on 24.10.2016.
 */
public class GraphicViewImpl extends JPanel implements GraphicView {

    private List<Shape> shapeList = new LinkedList<>();

    @Override
    public void receiveShape(Shape shape) {
        shapeList.add(shape);
        shape.addGraphicView(this);
        paintComponent(getGraphics());
    }

    @Override
    public void update() {
        paintComponent(getGraphics());
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (Shape shape : shapeList)
            shape.paint(graphics);
    }
}
