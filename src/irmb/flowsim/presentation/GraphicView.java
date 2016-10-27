package irmb.flowsim.presentation;

import irmb.flowsim.view.GraphicShape;

/**
 * Created by Sven on 19.10.2016.
 */
public interface GraphicView {
    void receiveShape(GraphicShape graphicShape);

    void update();
}
