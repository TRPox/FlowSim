package irmb.test.presentation;

import irmb.util.Observer;

/**
 * Created by Sven on 25.10.2016.
 */
public class ObserverSpy implements Observer {

    private boolean wasNotified;

    public boolean wasNotified() {
        return wasNotified;
    }

    @Override
    public void update() {
        wasNotified = true;
    }
}
