package irmb.util;

import java.util.List;

/**
 * Created by Sven on 27.10.2016.
 */
public abstract class Subject {

    protected List<Observer> observers;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    protected void notifyObservers() {
        for (Observer o : observers)
            o.update();
    }

}
