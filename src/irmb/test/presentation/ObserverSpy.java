package irmb.test.presentation;

import irmb.flowsim.presentation.Observer;

/**
 * Created by Sven on 25.10.2016.
 */
public class ObserverSpy implements Observer {

    private boolean wasNotified;

    public boolean wasNotified() {
        return wasNotified;
    }
}
