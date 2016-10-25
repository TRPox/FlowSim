package irmb.flowsim.model.geometry;

import irmb.flowsim.presentation.GraphicView;

import java.awt.*;

/**
 * Created by Sven on 20.10.2016.
 */
public abstract class Shape {

    protected GraphicView view;

    public void addGraphicView(GraphicView view) {
        this.view = view;
    }

    protected void notifyView() {
        if (view != null) view.update();
    }

    public abstract void paint(Graphics graphics);
}
