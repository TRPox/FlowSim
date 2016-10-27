package irmb.flowsim.view;

import irmb.flowsim.model.geometry.Shape;
import irmb.util.Observer;

import java.awt.*;

/**
 * Created by Sven on 20.10.2016.
 */
public abstract class GraphicShape implements Observer {

    protected Observer observer;
    protected Shape shape;

    public void addObserver(Observer observer) {
        this.observer = observer;
    }

    protected void notifyObserver() {
        if (observer != null) observer.update();
    }

    public abstract void paint(Graphics graphics);
}
