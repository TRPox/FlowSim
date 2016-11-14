package irmb.flowsim.view.graphics;

import irmb.flowsim.model.geometry.Shape;
import irmb.util.Observer;
import irmb.util.Subject;

import java.awt.*;

/**
 * Created by Sven on 20.10.2016.
 */
public abstract class GraphicShape extends Subject implements Observer {

    protected Shape shape;
    
    public abstract void paint(Graphics graphics);
}
