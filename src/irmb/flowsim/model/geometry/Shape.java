package irmb.flowsim.model.geometry;

import irmb.util.Subject;

/**
 * Created by Sven on 25.10.2016.
 */
public abstract class Shape extends Subject {

    public abstract void moveBy(double dx, double dy);

    public abstract boolean isPointOnBoundary(Point point, double radius);

}
