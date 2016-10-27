package irmb.flowsim.model.geometry;

import irmb.util.Observer;

/**
 * Created by Sven on 25.10.2016.
 */
public class Shape {
    protected Observer observer;

    public void addObserver(Observer observer) {
        this.observer = observer;
    }

    protected void notifyObserver() {
        if (observer != null) observer.update();
    }
}
