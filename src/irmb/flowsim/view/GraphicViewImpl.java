package irmb.flowsim.view;

import irmb.flowsim.presentation.GraphicView;
import irmb.flowsim.presentation.GraphicViewPresenter;
import irmb.util.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sven on 24.10.2016.
 */
public class GraphicViewImpl extends JPanel implements GraphicView, Observer {

    protected GraphicViewPresenter presenter;
    protected List<GraphicShape> graphicShapeList = new LinkedList<>();

    @Override
    public void receiveShape(GraphicShape graphicShape) {
        graphicShapeList.add(graphicShape);
        graphicShape.addObserver(this);
        paintComponent(getGraphics());
    }

    public void setPresenter(GraphicViewPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void update() {
        paintComponent(getGraphics());
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (GraphicShape graphicShape : graphicShapeList)
            graphicShape.paint(graphics);
    }
}
