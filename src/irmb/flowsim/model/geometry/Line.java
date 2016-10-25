package irmb.flowsim.model.geometry;

import irmb.flowsim.presentation.GraphicView;

import java.awt.*;

/**
 * Created by Sven on 20.10.2016.
 */
public class Line implements Shape {
    private Point start;
    private Point end;
    private GraphicView view;

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
        notifyView();
    }

    private void notifyView() {
        if (view != null) view.update();
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
        notifyView();
    }

    public void addGraphicView(GraphicView view) {
        this.view = view;
    }

    @Override
    public void paint(Graphics graphics) {

    }
}
