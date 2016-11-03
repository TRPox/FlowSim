package irmb.util;

import irmb.flowsim.view.GraphicViewImpl;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sven on 27.10.2016.
 */
public abstract class Subject {

    protected List<Observer> observers = new LinkedList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    protected void notifyObservers() {
        for (Observer o : observers)
            o.update();
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
}
